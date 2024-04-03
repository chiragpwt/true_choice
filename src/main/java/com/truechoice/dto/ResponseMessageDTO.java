package com.truechoice.dto;

import io.swagger.annotations.ApiModelProperty;

public class ResponseMessageDTO {

	public ResponseMessageDTO(Boolean status, MessageDTO data) {
		super();
		this.status = status;
		this.data = data;
	}

	@ApiModelProperty(position = 0)
	private Boolean status;
	@ApiModelProperty(position = 1)
	private MessageDTO data;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public MessageDTO getData() {
		return data;
	}

	public void setData(MessageDTO data) {
		this.data = data;
	}

	public ResponseMessageDTO() {
	}

}
