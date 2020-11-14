package com;

import java.util.Scanner;

class Range
{
	public int max;
	public int min;
}

public class Main
{
	/**
	 * Bubble Sort
	 * @param array
	 */
	private static void arrayLowestToGreatestSort(int[] array)
	{
		for (int i = 1; i < array.length - 1; ++i)
		{
			int s = i;

			while (array[s] < array[s - 1])
			{
				int tmp = array[s];
				array[s] = array[s - 1];
				array[s - 1] = tmp;

				if (s > 1)
					--s;
			}

			s = i;

			while (array[s] > array[s + 1])
			{
				int tmp = array[s + 1];
				array[s + 1] = array[s];
				array[s] = tmp;

				if (s > 0)
					--s;
			}
		}
	}

	private static void generateTicket()
	{
		Scanner input = new Scanner(System.in);
		int nTickets;

		do
		{
			printf("How many tickets do you want?\n");
			nTickets = input.nextInt();
		} while (nTickets == 0);

		printf("How many numbers do you want on your ticket?\n");
		int nNums = input.nextInt();
		Range range = new Range();

		do
			printf("Please specify the range of numbers to be generated. \"Ex: 1-420911\"\n");
		while (!parseRange(input.next(), range) || nNums > range.max - range.min);

		input.close();
		
		for (int i = 0; i < nTickets; ++i)
			generateTicketNumbers(nNums, range.max, range.min);
	}

	private static void generateTicketNumbers(int nNums, int max, int min)
	{
		int[] numbers = new int[nNums];

		for (int i = 0; i < nNums; ++i)
		{
			int next_num = 0;
			boolean good = false;

			while (!good)
			{
				next_num = randomInt(max, min);

				for (int num : numbers)
				{
					if (next_num == num)
					{
						good = false;
						break;
					}
					good = true;
				}
			}
			numbers[i] = next_num;
		}
		arrayLowestToGreatestSort(numbers);

		printf("\n -==Lottery Ticket==- \n");

		for (int i = 0; i < nNums; ++i)
			printf(numbers[i] + " ");
	}
	
	private static boolean parseRange(String input, Range range)
	{
		String[] buffer = input.split("-");

		if (buffer.length < 2)
		{
			printf("[Range Parser]: Could not parse range! Please specify more than one number!\n");
			return false;
		}

		range.min = Integer.parseInt(buffer[0]);
		range.max = Integer.parseInt(buffer[1]);

		return true;
	}

	private static void printf(String... args)
	{
		String out = "";

		for (String str : args)
			out += str;

		System.out.print(out);
	}

	private static int randomInt(int max, int min)
	{
		return (int) (Math.random() * (max - min + 1) + min);
	}

	public static void main(String[] args)
	{
		generateTicket();
	}
}