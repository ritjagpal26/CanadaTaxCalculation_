package com.example.canadataxcalculation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ShowData extends AppCompatActivity {
        public  TextView getFullname;
        public  TextView getSinNumber;
        public TextView getFt;
        public TextView getDob;
        private TextView getCpp;
        private  TextView getPt;
        private TextView  getTotaltaxammount;
    private TextView  getGEnder;
    private TextView totalTax;
    private TextView rrsp;
    private TextView Ei;


//    CRACustomer craCustomer = getIntent().getParcelableExtra("custobject");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        CRACustomer craCustomer = getIntent().getParcelableExtra("custobject");

        getFullname = findViewById(R.id.getNAme);
        getFt = findViewById(R.id.getFedralTax);
        getCpp = findViewById(R.id.getCpp);
        getSinNumber = findViewById(R.id.getSinnumber);
        getDob = findViewById(R.id.getAge);
        getPt = findViewById(R.id.getPt);
        getTotaltaxammount = findViewById(R.id.totaltaxable);

        totalTax = findViewById(R.id.taxammount);

        rrsp = findViewById(R.id.rrspget);
        Ei = findViewById(R.id.Ei);



        getFullname.setText(craCustomer.fullName());
        getCpp.setText(String.valueOf(craCustomer.cppAmount()));
        double tempcpp = craCustomer.cppAmount();
        double tempEi = craCustomer.EI(craCustomer.grossIncome);
        double temprrsp = craCustomer.rrspAmount(craCustomer.grossIncome);


        double tempttti = craCustomer.total_taxable_amount(craCustomer.maxrssp,tempEi,tempcpp);
        double temtt = craCustomer.totaltaxammount(craCustomer.provincialATx(tempttti),craCustomer.fedraltax(tempttti));
        getFt.setText(String.valueOf(Float.valueOf(craCustomer.fedraltax(tempttti))));
        getPt.setText(String.valueOf(Float.valueOf((float) craCustomer.provincialATx(tempttti))));
        getDob.setText(String.valueOf((craCustomer.calculateAge())));
        getSinNumber.setText(String.valueOf(craCustomer.sinNumber));
        getTotaltaxammount.setText(String.valueOf(Float.valueOf((float)tempttti)));
//        getGEnder.setText(craCustomer.gender);
        totalTax.setText(String.valueOf(Float.valueOf((float) temtt)));
        rrsp.setText(String.valueOf(Float.valueOf(craCustomer.rrspAmount(craCustomer.grossIncome))));
        Ei.setText(String.valueOf(Float.valueOf(craCustomer.EI(craCustomer.grossIncome))));



    }


}
