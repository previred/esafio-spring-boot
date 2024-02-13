package com.moveapps.management.user.domains.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private String id;
	private String firstName;
	private String lastName;
	private String username;
    private String password;
	private String phoneNumber;
	private String status;
}