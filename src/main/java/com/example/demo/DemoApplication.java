package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

	char [] hindiArray = new char[]{0x0905, 0x0906, 0x0907, 0x090A, 0x0910, 0x090F
			, 0x0913, 0x0914, 0x0915, 0x0916, 0x0917, 0x0918, 0x091A, 0x091B, 0x091C, 0x091D,
			0x091F, 0x0920, 0x0920, 0x0921, 0x0922, 0x0924, 0x0925, 0x0926, 0x0927, 0x0928,
			0x092A,0x092B, 0x092C,0x092D,0x092E, 0x092F, 0x0930, 0x0932,0x0935, 0x0938,0x0939};

	int [] intArray = new int[90];
	int [] cardArray = new int[90];
	StringBuffer stringBuffer = new StringBuffer();

	{
		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = i + 1;
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping
	public ModelAndView getCharacter(ModelAndView modelAndView) {
		int random = (int)(Math.random() * hindiArray.length);
		modelAndView.setViewName("home");
		modelAndView.addObject("ch", hindiArray[random]);
		return modelAndView;
	}

	@GetMapping("/housie")
	public ModelAndView getHousieNumber(ModelAndView modelAndView) {
		int random = (int)(Math.random() * intArray.length);
		modelAndView.setViewName("housie");
		if(intArray.length == 0) {
			modelAndView.addObject("number", "Full House!");
		} else {
			modelAndView.addObject("number", intArray[random]);
			intArray = removeHousieNumber(intArray, random);
		}
		return modelAndView;
	}

	@GetMapping("housie-cards")
	public ModelAndView printHousieNumbers(ModelAndView modelAndView) {
		modelAndView.setViewName("housie-cards");
		if(intArray.length == 90) {
			modelAndView.addObject("cards", "Let's begin!");
		} else {
			modelAndView.addObject("cards", stringBuffer.toString());
		}
		return modelAndView;
	}

	public int[] removeHousieNumber(int [] numberArray, int index) {
		int removeNumber = numberArray[index];
		stringBuffer.append(removeNumber + " ");
		int [] copy = new int[numberArray.length - 1];
		for (int i = 0, j = 0; i < numberArray.length; i++) {
			if(i != index) {
				copy[j++] = numberArray[i];
			}
		}
		cardArray[index] = removeNumber;
		return copy;
	}

	public int[] trimmedCard(int [] cardArray) {
		int counter = 0;
		for (int i = 0; i < cardArray.length; i++) {
			if(cardArray[i] != 0)
				counter++;
		}
		int [] copy = new int[counter];
		for (int i = 0, j = 0; i < cardArray.length; i++) {
			if(cardArray[i] != 0) {
				copy[j++] = cardArray[i];
			}
		}
		return copy;
	}
}
