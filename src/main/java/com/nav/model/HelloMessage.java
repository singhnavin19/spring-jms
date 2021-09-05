package com.nav.model;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HelloMessage implements Serializable {

	static final long serialVersionUID = -4545529412L;

	private UUID uid;
	private String message;
}
