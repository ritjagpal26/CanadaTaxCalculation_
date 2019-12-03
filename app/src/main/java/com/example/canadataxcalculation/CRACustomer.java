package com.example.canadataxcalculation;

import android.os.Parcel;
import android.os.Parcelable;

public class CRACustomer implements Parcelable {

    public String fNAme;
    public String lName;
    public String dob;
    public int sinNumber;
    public double grossIncome;
    public double rrspCont;
    public   String gender;
    public  double maxrssp;

    public  double tti;



       public CRACustomer()
       {

       }




    public CRACustomer(String fNAme, String lName, String dob, int sinNumber,double grossIncome, double rrspCont,String gender) {
        this.fNAme = fNAme;
        this.lName = lName;
        this.dob = dob;
        this.sinNumber = sinNumber;
        this.grossIncome = grossIncome;
        this.rrspCont = rrspCont;
        this.gender = gender;
//        this.total_taxable_amount = total_taxable_amount;
    }
      public String getGender() {
          return gender;
      }

      public void setGender(String gender) {
          this.gender = gender;
      }



    public String getfNAme() {
        return fNAme;
    }

    public void setfNAme(String fNAme) {
        this.fNAme = fNAme;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getSinNumber() {
        return sinNumber;
    }

    public void setSinNumber(int sinNumber) {
        this.sinNumber = sinNumber;
    }

    public double getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(double grossIncome) {
        this.grossIncome = grossIncome;
    }

    public double getRrspCont() {
        return rrspCont;
    }

    public void setRrspCont(double rrspCont) {
        this.rrspCont = rrspCont;
    }

    public static Creator<CRACustomer> getCREATOR() {
        return CREATOR;
    }



    protected CRACustomer(Parcel in) {

        fNAme = in.readString();
        lName = in.readString();
        dob = in.readString();
        sinNumber = in.readInt();
        grossIncome = in.readDouble();
        rrspCont = in.readDouble();

    }

    public static final Creator<CRACustomer> CREATOR = new Creator<CRACustomer>() {
        @Override
        public CRACustomer createFromParcel(Parcel in) {
            return new CRACustomer(in);
        }

        @Override
        public CRACustomer[] newArray(int size) {
            return new CRACustomer[size];
        }
    };

    public String fullName() {

        String Fullname = lName.toUpperCase() + "," + fNAme.substring(0, 1).toUpperCase() + fNAme.substring(1).toLowerCase();
        return Fullname;
    }


    public double cppAmount() {

        System.out.println(gender);
        System.out.println("Print rrsp"+rrspCont);
        double cpp_max = 57400.00;
        double cpp_contribution_rate = 5.10;
        double total_cpp;
        if (grossIncome >= cpp_max) {
                        System.out.println("grossincpp "+grossIncome);

            total_cpp = (cpp_max * cpp_contribution_rate) / 100;
        } else {
            total_cpp = (grossIncome * cpp_contribution_rate) / 100;
                        System.out.println("grosselsecpp "+grossIncome);

        }
        System.out.println(total_cpp);
                return Float.valueOf((float) total_cpp);

    }

    public int calculateAge()
    {
        int age = 2019 - Integer.valueOf(dob);
        return  age;
    }


    public float EI(Double grossIncome) {

                double eIContribution = 0.00;
                System.out.println("in loop gi" + grossIncome);
                if (grossIncome >= 53100){
                    System.out.println("grossIfEI " + grossIncome);

                    eIContribution = 53100 * 0.0162;
                    System.out.println("ifEI " + eIContribution);
                    return Float.valueOf((float) eIContribution);

                } else{

                    eIContribution = grossIncome * 0.0162;
                    System.out.println("grossWElseEI " + grossIncome);

                    System.out.println("elseEI " + eIContribution);
                    return Float.valueOf((float) eIContribution);
                }


            }


    public float rrspAmount(Double grossIncome) {
        double rrsp_max;

        rrsp_max = (grossIncome * .18);
        maxrssp = rrsp_max;
        if (rrsp_max > rrspCont) {
            double carry_forwaded_rrsp = rrsp_max - rrspCont;

            System.out.println("rrspcontcarry "+carry_forwaded_rrsp);
            return Float.valueOf((float)carry_forwaded_rrsp);
        }
             else {
            System.out.println("rrspcont "+rrspCont);
        return Float.valueOf((float)rrsp_max);         }

    }

    public Double total_taxable_amount(double rrspCont, double EI, double cpp)
    {
        System.out.println("rrsp in tti"+rrspAmount(grossIncome));
        tti = grossIncome - (cpp +rrspCont + EI);
        return tti;
    }




public float fedraltax(Double grossIncome)
{
    double FT = 0.0;

    if(grossIncome > 0.0 && grossIncome <= 12069)
    {
        FT = 0.0;
    }
    else if(grossIncome >= 12069.1 && grossIncome <= 47630)
    {
        grossIncome = grossIncome-12069;
        FT = grossIncome * .15;
    }
    else if(grossIncome >= 47630.01 && grossIncome <= 95259)
    {

        grossIncome = grossIncome-47630;
        double backtax = (47630-12096)*.15;
        double temptax = grossIncome * .205;
        FT = backtax + temptax;
    }
    else if(grossIncome >= 95259.01 && grossIncome <= 147667)
    {

        grossIncome = grossIncome-95259;
        double backtax = (47630.01-12096.1)*.15;
        double backtax2 = ((95259.01-47630.01)*20.5)/100;
        double temptax = (grossIncome * 26.0)/100;

        FT = backtax + backtax2 + temptax;
    }
    else if(grossIncome >= 147667.01 && grossIncome <= 210371)
    {

        grossIncome = grossIncome-147667.00;
        double backtax = ((47630.01-12096.01)*15)/100;
        double backtax2 = ((95259-47630.00)*20.5)/100;
        double backtax3 = ((147667.00-95259.00)*26.0)/100;
        double temptax = (grossIncome * 29.0)/100;

        FT = backtax + backtax2 + backtax3 + temptax;
    }
    else if(grossIncome >= 210371.01)
    {

        grossIncome = grossIncome-210371.01;
        double backtax = ((47630.01-12096)*15)/100;
        double backtax2 = ((95259.01-47630.01)*20.50)/100;
        double backtax3 = ((147667.01-95259.01)*26)/100;
        double backtax4 = ((210371.01-147667.01)*29)/100;

        double temptax = (grossIncome * 33)/100;

        FT = backtax + backtax2 + backtax3 + backtax4 + temptax;
    }



    return (float) FT;
}
    public float provincialATx(Double grossIncome)
    {
        double PT = 0.0;

        if(grossIncome > 0.0 && grossIncome <= 10582.0)
        {
            PT = 0.0;
        }
        else if(grossIncome >= 10582.1 && grossIncome <= 43906.0)
        {
            grossIncome = grossIncome-10582;
            PT = (grossIncome * 5.05)/100;
        }
        else if(grossIncome >= 43906.0 && grossIncome <= 87813.0)
        {

            grossIncome = grossIncome-43906;
            double backtax = ((43906-10582)*5.05)/100;
            double temptax = (grossIncome * 9.15)/100;
            PT = backtax + temptax;
        }
        else if(grossIncome >= 87813.0 && grossIncome <= 150000.0)
        {

            grossIncome = grossIncome-87813.0;
            double backtax = ((43906-10582)*5.05)/100;
            double backtax2 = ((87813-43906)*9.15)/100;
            double temptax = (grossIncome * 11.16)/100;

            PT = backtax + backtax2 + temptax;
        }
        else if(grossIncome >= 150000.0 && grossIncome <= 220000.0)
        {

            grossIncome = grossIncome-150000.0;
            double backtax = ((43906-10582)*5.05)/100;
            double backtax2 = ((87813-43906)*9.15)/100;
            double backtax3 = ((150000-87813)*11.16)/100;
            double temptax = (grossIncome * 12.16)/100;

            PT = backtax + backtax2 + backtax3 + temptax;
        }
        else if(grossIncome >= 220000.0)
        {

            grossIncome = grossIncome-220000;
            double backtax = ((43906-10582)*5.05)/100;
            double backtax2 = ((87813-43906)*9.15)/100;
            double backtax3 = ((150000-87813)*11.16)/100;
            double backtax4 = ((220000-150000)*12.16)/100;

            double temptax = (grossIncome * 13.16)/100;

            PT = backtax + backtax2 + backtax3 + backtax4 + temptax;
        }



        return (float) PT;
    }




      public double totaltaxammount(double pt,double ft) {

        return provincialATx(tti)+fedraltax(tti);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fNAme);
        dest.writeString(lName);
        dest.writeString(dob);
        dest.writeInt(sinNumber);
        dest.writeDouble(grossIncome);
        dest.writeDouble(rrspCont);

    }
}























































































































































































































































































