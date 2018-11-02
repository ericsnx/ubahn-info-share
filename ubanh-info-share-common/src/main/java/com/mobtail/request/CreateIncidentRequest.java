package com.mobtail.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateIncidentRequest extends AbstractIncidentRequest {

    @Builder
    public CreateIncidentRequest(final String line, final String user) {
        super(line, user);
    }
}


