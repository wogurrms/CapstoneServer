package kr.ac.hansung.cse.model;

import javax.validation.constraints.Min;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class Customer {
	private int id;
	private String date;
	
	@Min(value=0, message="The count must not be less than 0")
	private int count;
}
