package com.nacu.sport.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule
{
    @Field(type = FieldType.Date, format = DateFormat.hour_minute_second)
    private LocalTime mondayStart;

    @Field(type = FieldType.Date, format = DateFormat.hour_minute_second)
    private LocalTime mondayEnd;

    @Field(type = FieldType.Date, format = DateFormat.hour_minute_second)
    private LocalTime tuesdayStart;

    @Field(type = FieldType.Date, format = DateFormat.hour_minute_second)
    private LocalTime tuesdayEnd;

    @Field(type = FieldType.Date, format = DateFormat.hour_minute_second)
    private LocalTime wednesdayStart;

    @Field(type = FieldType.Date, format = DateFormat.hour_minute_second)
    private LocalTime wednesdayEnd;

    @Field(type = FieldType.Date, format = DateFormat.hour_minute_second)
    private LocalTime thursdayStart;

    @Field(type = FieldType.Date, format = DateFormat.hour_minute_second)
    private LocalTime thursdayEnd;

    @Field(type = FieldType.Date, format = DateFormat.hour_minute_second)
    private LocalTime fridayStart;

    @Field(type = FieldType.Date, format = DateFormat.hour_minute_second)
    private LocalTime fridayEnd;

    @Field(type = FieldType.Date, format = DateFormat.hour_minute_second)
    private LocalTime saturdayStart;

    @Field(type = FieldType.Date, format = DateFormat.hour_minute_second)
    private LocalTime saturdayEnd;

    @Field(type = FieldType.Date, format = DateFormat.hour_minute_second)
    private LocalTime sundayStart;

    @Field(type = FieldType.Date, format = DateFormat.hour_minute_second)
    private LocalTime sundayEnd;
}
