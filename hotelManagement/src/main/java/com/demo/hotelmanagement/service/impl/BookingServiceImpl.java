package com.demo.hotelmanagement.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.demo.hotelmanagement.dao.BookingDao;
import com.demo.hotelmanagement.dao.GuestDao;
import com.demo.hotelmanagement.dao.HotelDao;
import com.demo.hotelmanagement.dao.PriceDetailsDao;
import com.demo.hotelmanagement.dao.RoomOptionsDao;
import com.demo.hotelmanagement.dao.UserDao;
import com.demo.hotelmanagement.dto.BookingDetailsRequestDto;
import com.demo.hotelmanagement.dto.BookingRequestDto;
import com.demo.hotelmanagement.dto.GuestRequestDto;
import com.demo.hotelmanagement.dto.ResponseDto;
import com.demo.hotelmanagement.dto.RoomDetailDto;
import com.demo.hotelmanagement.model.Booking;
import com.demo.hotelmanagement.model.Guest;
import com.demo.hotelmanagement.model.Hotel;
import com.demo.hotelmanagement.model.PriceDetails;
import com.demo.hotelmanagement.model.RoomOptions;
import com.demo.hotelmanagement.model.RoomType;
import com.demo.hotelmanagement.model.User;
import com.demo.hotelmanagement.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	UserDao userDao;
	@Autowired
	GuestDao guestDao;
	@Autowired
	BookingDao bookingDao;
	@Autowired
	HotelDao hotelDao;
	@Autowired
	RoomOptionsDao roomOptionsDao;
	@Autowired
	PriceDetailsDao priceDetailsDao;

	@Override
	public ResponseDto bookRoom(List<BookingRequestDto> bookingRequestDtoList,String roomType,Long roomOptionId) {

		ResponseDto responseDto = new ResponseDto();

		List<Boolean> verifyList = bookingRequestDtoList.stream()
				.map(bookingRequestDto -> validateUserIdAndRoomId(bookingRequestDto,roomOptionId)).collect(Collectors.toList());

		if (verifyList.contains(false)) {

			responseDto.setMessage("Please verify roomId and UserId entered");
			responseDto.setStatusCode(HttpStatus.NOT_FOUND.value());

			return responseDto;
		}

		bookingRequestDtoList.stream().map(bookingRequestDto -> addBookingRequestDto(bookingRequestDto,roomType,roomOptionId))
				.collect(Collectors.toList());

		responseDto.setMessage("Booking confirmed");
		responseDto.setStatusCode(HttpStatus.CREATED.value());

		return responseDto;
	}

	private Boolean validateUserIdAndRoomId(BookingRequestDto bookingRequestDto,Long roomOptionId) {

		Optional<RoomOptions> roomOptional = roomOptionsDao.findByRoomOptionId(roomOptionId);
		Optional<User> userOptional = userDao.findByUserId(bookingRequestDto.getUserId());
		if (!roomOptional.isPresent() || (!userOptional.isPresent()))
			return false;

		return true;

	}

	private BookingRequestDto addBookingRequestDto(BookingRequestDto bookingRequestDto,String roomType,Long roomOptionId) {
		

		Booking booking = new Booking();

		booking.setUserId(bookingRequestDto.getUserId());
		booking.setCheckIn(LocalTime.parse(bookingRequestDto.getCheckIn()));
		booking.setCheckOut(LocalTime.parse(bookingRequestDto.getCheckOut()));
		booking.setDate(LocalDate.parse(bookingRequestDto.getDate()));
		booking.setRoomId(roomOptionId);
		bookingDao.save(booking);

		Optional<RoomOptions> roomOptional = roomOptionsDao.findByRoomOptionId(roomOptionId);
		
		if (!roomOptional.isPresent())
			return new BookingRequestDto("Please verify room details");
		
		Optional<Hotel> hotelOptional = hotelDao.findByHotelId(roomOptional.get().getHotelId());
		if(!hotelOptional.isPresent())
			return new BookingRequestDto("Please verify hotel details");
		
		if(roomType.equalsIgnoreCase((RoomType.DOUBLE).toString()))
		{
			roomOptional.get().setDoubleRoom(roomOptional.get().getDoubleRoom()-1);
			roomOptionsDao.save(roomOptional.get());
		}
			if(roomType.equalsIgnoreCase((RoomType.TRIPLE).toString()))
			{
				roomOptional.get().setDoubleRoom(roomOptional.get().getTripleRoom()-1);
			roomOptionsDao.save(roomOptional.get());
			}
			if(roomType.equalsIgnoreCase((RoomType.FAMILY).toString()))
			{
				roomOptional.get().setDoubleRoom(roomOptional.get().getFamilyRoom()-1);
			roomOptionsDao.save(roomOptional.get());
			}
			if(roomType.equalsIgnoreCase((RoomType.SINGLE).toString()))
			{
				roomOptional.get().setDoubleRoom(roomOptional.get().getSingleRoom()-1);
			roomOptionsDao.save(roomOptional.get());
			}
			
			if(roomType.equalsIgnoreCase((RoomType.VILLA).toString()))
			{
				roomOptional.get().setDoubleRoom(roomOptional.get().getVilla()-1);
			roomOptionsDao.save(roomOptional.get());
			}
				
			
			hotelOptional.get().setRoomsAvailable(hotelOptional.get().getRoomsAvailable() - 1);
			hotelDao.save(hotelOptional.get());
			
		
		bookingRequestDto.getGuestRequestDtoList().stream()
				.map(guestRequestDto -> addGuestRequestDto(guestRequestDto, booking.getBookingId()))
				.collect(Collectors.toList());
		bookingRequestDto.setMessage("booking confirmed");
		return bookingRequestDto;

	}

	private GuestRequestDto addGuestRequestDto(GuestRequestDto guestRequestDto, Long bookingId) {

		Guest guest = new Guest();
		guest.setAge(guestRequestDto.getAge());
		guest.setGuestName(guestRequestDto.getGuestName());
		guest.setBookingId(bookingId);
		guestDao.save(guest);

		return guestRequestDto;

	}

	@Override
	public RoomDetailDto getCustomeHotelDetails(BookingDetailsRequestDto bookingDetailsRequestDto) {
		
		
		RoomDetailDto roomDetailsDto = new RoomDetailDto();
		if(bookingDetailsRequestDto.getGuests() >20)
			return new  RoomDetailDto("You cannot book for more than 1o guests");
		
		
	int room = 	bookingDetailsRequestDto.getGuests() /bookingDetailsRequestDto.getRoomsRequired();
	if(room> 5)
	{
		Optional<Hotel> hotelOptional = hotelDao.findByHotelId(bookingDetailsRequestDto.getHotelId());
		if(!hotelOptional.isPresent())
			return roomDetailsDto;
		Optional<RoomOptions> roomOptional = roomOptionsDao.findByHotelId(bookingDetailsRequestDto.getHotelId());
		if(!roomOptional.isPresent())
			return roomDetailsDto;
		Optional<RoomOptions> roomOptions = roomOptionsDao.findByRoomId(roomOptional.get().getRoomId());
		if(!roomOptions.isPresent())
			return roomDetailsDto;
		Optional<PriceDetails> priceDetails = priceDetailsDao.findByRoomOptionIdAndRoomType(roomOptions.get().getRoomOptionId(), RoomType.VILLA);
		if(!priceDetails.isPresent())
			return new RoomDetailDto("No rooms available");
		
		roomDetailsDto.setAvailable(roomOptions.get().getFamilyRoom());
		roomDetailsDto.setPrice(priceDetails.get().getPrice());
		roomDetailsDto.setRoomType(RoomType.VILLA);
		roomDetailsDto.setHotelName(hotelOptional.get().getHotelName());
		roomDetailsDto.setMessage("Please find the above room details");
		roomDetailsDto.setRoomOptionId(roomOptional.get().getRoomId());
		return roomDetailsDto;
		
	}
	if(room ==2)
	{
		Optional<Hotel> hotelOptional = hotelDao.findByHotelId(bookingDetailsRequestDto.getHotelId());
		if(!hotelOptional.isPresent())
			return roomDetailsDto;
		Optional<RoomOptions> roomOptional = roomOptionsDao.findByHotelId(bookingDetailsRequestDto.getHotelId());
		if(!roomOptional.isPresent())
			return roomDetailsDto;
		Optional<RoomOptions> roomOptions = roomOptionsDao.findByRoomId(roomOptional.get().getRoomId());
		if(!roomOptions.isPresent())
			return roomDetailsDto;
		Optional<PriceDetails> priceDetails = priceDetailsDao.findByRoomOptionIdAndRoomType(roomOptions.get().getRoomOptionId(), RoomType.DOUBLE);
		if(!priceDetails.isPresent())
			return new RoomDetailDto("No rooms available");
		
		roomDetailsDto.setAvailable(roomOptions.get().getFamilyRoom());
		roomDetailsDto.setPrice(priceDetails.get().getPrice());
		roomDetailsDto.setRoomType(RoomType.DOUBLE);
		roomDetailsDto.setHotelName(hotelOptional.get().getHotelName());
		roomDetailsDto.setRoomOptionId(roomOptional.get().getRoomId());
		roomDetailsDto.setMessage("Please find the above room details");;
		return roomDetailsDto;
		
	}	
	if(room ==1)
	{
		Optional<Hotel> hotelOptional = hotelDao.findByHotelId(bookingDetailsRequestDto.getHotelId());
		if(!hotelOptional.isPresent())
			return roomDetailsDto;
		Optional<RoomOptions> roomOptional = roomOptionsDao.findByHotelId(bookingDetailsRequestDto.getHotelId());
		if(!roomOptional.isPresent())
			return roomDetailsDto;
		Optional<RoomOptions> roomOptions = roomOptionsDao.findByRoomId(roomOptional.get().getRoomId());
		if(!roomOptions.isPresent())
			return roomDetailsDto;
		Optional<PriceDetails> priceDetails = priceDetailsDao.findByRoomOptionIdAndRoomType(roomOptions.get().getRoomOptionId(), RoomType.SINGLE);
		if(!priceDetails.isPresent())
			return new RoomDetailDto("No rooms available");
		
		roomDetailsDto.setAvailable(roomOptions.get().getFamilyRoom());
		roomDetailsDto.setPrice(priceDetails.get().getPrice());
		roomDetailsDto.setRoomType(RoomType.SINGLE);
		roomDetailsDto.setHotelName(hotelOptional.get().getHotelName());
		roomDetailsDto.setRoomOptionId(roomOptional.get().getRoomId());
		roomDetailsDto.setMessage("Please find the above room details");;
		return roomDetailsDto;
		
	}
	
	if(room ==3)
	{
		Optional<Hotel> hotelOptional = hotelDao.findByHotelId(bookingDetailsRequestDto.getHotelId());
		if(!hotelOptional.isPresent())
			return roomDetailsDto;
		Optional<RoomOptions> roomOptional = roomOptionsDao.findByHotelId(bookingDetailsRequestDto.getHotelId());
		if(!roomOptional.isPresent())
			return roomDetailsDto;
		Optional<RoomOptions> roomOptions = roomOptionsDao.findByRoomId(roomOptional.get().getRoomId());
		if(!roomOptions.isPresent())
			return roomDetailsDto;
		Optional<PriceDetails> priceDetails = priceDetailsDao.findByRoomOptionIdAndRoomType(roomOptions.get().getRoomOptionId(), RoomType.TRIPLE);
		if(!priceDetails.isPresent())
			return new RoomDetailDto("No rooms available");
		
		roomDetailsDto.setAvailable(roomOptions.get().getFamilyRoom());
		roomDetailsDto.setPrice(priceDetails.get().getPrice());
		roomDetailsDto.setRoomType(RoomType.TRIPLE);
		roomDetailsDto.setHotelName(hotelOptional.get().getHotelName());
		roomDetailsDto.setRoomOptionId(roomOptional.get().getRoomId());
		roomDetailsDto.setMessage("Please find the above room details");;
		return roomDetailsDto;
		
	}
	if(room ==4)
	{
		Optional<Hotel> hotelOptional = hotelDao.findByHotelId(bookingDetailsRequestDto.getHotelId());
		if(!hotelOptional.isPresent())
			return roomDetailsDto;
		Optional<RoomOptions> roomOptional = roomOptionsDao.findByHotelId(bookingDetailsRequestDto.getHotelId());
		if(!roomOptional.isPresent())
			return roomDetailsDto;
		Optional<RoomOptions> roomOptions = roomOptionsDao.findByRoomId(roomOptional.get().getRoomId());
		if(!roomOptions.isPresent())
			return roomDetailsDto;
		Optional<PriceDetails> priceDetails = priceDetailsDao.findByRoomOptionIdAndRoomType(roomOptions.get().getRoomOptionId(), RoomType.FAMILY);
		if(!priceDetails.isPresent())
			return new RoomDetailDto("No rooms available");
		
		roomDetailsDto.setAvailable(roomOptions.get().getFamilyRoom());
		roomDetailsDto.setPrice(priceDetails.get().getPrice());
		roomDetailsDto.setRoomType(RoomType.FAMILY);
		roomDetailsDto.setHotelName(hotelOptional.get().getHotelName());
		roomDetailsDto.setRoomOptionId(roomOptional.get().getRoomId());
		roomDetailsDto.setMessage("Please find the above room details");;
		return roomDetailsDto;
		
	}
	else
		return new RoomDetailDto("No rooms available ");
	}
}
