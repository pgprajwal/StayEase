package com.crio.stayEase.exchanges;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookRoomRequest {

    private int guests;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;
}
