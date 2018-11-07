package com.mobtail.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateIncidentRequest extends AbstractIncidentRequest {

    @NotNull(message = "incident id is required")
    private UUID id;

    @Builder
    public UpdateIncidentRequest(final UUID id, final String line, final String user) {
        super(line, user);
        this.id = id;
    }
}
