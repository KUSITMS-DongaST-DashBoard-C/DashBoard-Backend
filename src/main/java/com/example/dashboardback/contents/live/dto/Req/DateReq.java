package com.example.dashboardback.contents.live.dto.Req;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DateReq {

    private String startDate;

    private String endDate;
}
