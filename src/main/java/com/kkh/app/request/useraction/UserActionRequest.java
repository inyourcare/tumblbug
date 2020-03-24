package com.kkh.app.request.useraction;

import lombok.Data;

import java.util.UUID;

@Data
public class UserActionRequest {
    UUID projectUUID;
    long donationMoney;
}
