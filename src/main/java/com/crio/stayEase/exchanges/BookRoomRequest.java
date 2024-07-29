package com.crio.stayEase.exchanges;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookRoomRequest {

    private int roomsBooked;

    private int guests;

    private Date checkInDate;

    private Date checkOutDate;
}
