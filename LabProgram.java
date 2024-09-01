import java.util.Scanner;

public class LabProgram{
	
	public static void main(String args[]){
		Scanner scnr = new Scanner(System.in);
		
		int wages = scnr.nextInt();
		int taxableInterest = scnr.nextInt();
		int unemploymentCompensation = scnr.nextInt();
		int status = scnr.nextInt();
		int taxesWithheld = scnr.nextInt();
		
		int agi = calcAGI(wages, taxableInterest, unemploymentCompensation);
        System.out.printf("AGI: $%,d\n", agi);
		
		int deduction = getDeduction(status);
		System.out.printf("Deduction: $%,d\n", deduction);
		
		int taxable = calcTaxable(agi, deduction);
		System.out.printf("Taxable income: $%,d\n", taxable);
		
		int tax = calcTax(status, taxable);
		System.out.printf("Federal tax: $%,d\n", tax);
		
		int total = calcTaxDue(taxesWithheld, tax);
		System.out.printf("Tax due: $%,d\n", total);


	}
	
	 public static int calcAGI(int wages, int taxableInterest, int unemploymentCompensation) 
	 {
        if (wages < 0) wages = -wages;
        if (taxableInterest < 0) taxableInterest = -taxableInterest;
        if (unemploymentCompensation < 0) unemploymentCompensation = -unemploymentCompensation;

        int agi = wages + taxableInterest + unemploymentCompensation;
		return agi;
		
	 }

	public static int getDeduction(int status)
	{
		if (status == 0)
		{
			return 6000;
		}
		else if (status == 1)
		{
			return 12000;
		}
		else if (status == 2)
		{
			return 24000;
		}
		else
		{
			return 6000;
		}
	}
	
	public static int calcTaxable(int agi, int ded)
	{
		
		int tax = agi - ded;
		
		if (tax <= 0)
		{
			tax = 0;
		}
		
		return tax;
	}
	
	public static int calcTax(int s, int i)
	{
		double tax = 0;
		if (s == 0 || s == 1)
		{
			if (i <= 10000 && i >= 0)
			{
				tax = i * 0.1;
			}
			else if (i <= 40000)
			{
				tax = ((i - 10000)*0.12) + 1000;
			}
			else if (i <= 85000)
			{
				tax = ((i - 40000)*0.22) + 4600;
			}
			else
			{
				tax = ((i - 85000)*0.24) + 14500;
			}
		}
		
		if (s == 2)
		{
			if (i <= 20000 && i >= 0)
			{
				tax = i * 0.1;
			}
			else if (i <= 80000)
			{
				tax = ((i - 20000)*0.12) + 2000;
			}
			else
			{
				tax = ((i - 80000)*0.22) + 9200;
			}
		}
		
		int finalTax = (int) Math.round(tax);
		return finalTax;
	}
	
	public static int calcTaxDue(int w, int tax)
	{
		if (w < 0)
		{
			w = 0;
		}
		return tax - w;
	}
}