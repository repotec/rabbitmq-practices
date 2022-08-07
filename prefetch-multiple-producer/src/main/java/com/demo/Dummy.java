package com.demo;

import java.time.LocalDateTime;

public class Dummy {
	private long id;
	private LocalDateTime datetime;
	
	public Dummy() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	public Dummy(long id, LocalDateTime datetime) {
		super();
		this.id = id;
		this.datetime = datetime;
	}

	@Override
	public String toString() {
		return "Dummy [id=" + id + ", datetime=" + datetime + "]";
	}
}
