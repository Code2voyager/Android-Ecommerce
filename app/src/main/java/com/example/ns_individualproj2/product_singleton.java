package com.example.ns_individualproj2;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class product_singleton {

    FirebaseDatabase database;
    DatabaseReference myRef;
    private final List<products> product_data =new ArrayList<>();

    private static product_singleton  prodsingleton;

    public static product_singleton getProdsingleton(){
            if(prodsingleton  == null){
                prodsingleton =new product_singleton();
            }
            return prodsingleton;
    }

        private product_singleton(){
            //////            store data
//            products product1 = new products("p101","Kids Blue Jumpsuit", "pimage1","Denim Machine Wash.\n" +
//                    "This jean overall is suitable for 6-24 month,2-5 years baby toddler little girl boy\n" +
//                    "Adjustable straps,metal hook and snap closure,stone washed soft to wear.\n",85.45,"1");
//            products product2 = new products("p102"," Black Run Hoodie", "pimage2","Hand Wash Only .Soft touch feeling, comfortable to wear.Features- Unique tie dye color block oversized pullover sweatshirts for women, adjustable drawstring on hood, kangaroo pockets in front, long sleeves and a relaxed fit\n",30.00,"1");
//            products product3 = new products("p103","Blue Jeans And White Top", "pimage3","100% Cotton MachineWash Match All Styles: women button down shirts can be matched with jeans, skinny leggings, hot pants, fashion and vintage, long sleeve button shirt is a must-have for any ladies",120.25,"1");
//
//            products product4 = new products("p104","Fur Cream Jacket", "men_clothes","[Fabric]Shell: cotton; Lining Warm, Heavyweight, Soft, Comfortable.\n" +
//                    " The trucker jacket helps in keeping you warm with turn down collar and warm sherpa lining. You can wear it in cold winter.",105.45,"1");
//            products product5 = new products("p105","Pink Long Skirt", "women_dress","100% Rayon Features:High waist/elastic waist with drawstring/A-line long skirt.The high-waisted design shows off your waistline and can be worn with any style of top.",130.00,"1");
//            products product6 = new products("p106","Girl's Pink Mini Dress", "kids_dress","20% Cotton, 80% Polyester. Silky lace,soft smooth touch, Non-stretch fabric.High Waist Pleated A-line Casual Flower Girl Dress for little kid featured Concealed back zip,Tailored bodice and Knee length.",80.25,"1");
//
//            products product7 = new products("p107","Mens formals", "men_formals","The long sleeve dress shirt is made of high-quality stretch fabric,soft and comfortable with good breathability, wrinkle-free.The men's business shirt features spread collar, long sleeves provide you fashion looking.",70.45,"1");
//            products product8 = new products("p108","Women Formals", "women_formals","Women's basic blazer, two functional pockets, long sleeves, solid color, lightweight, notched lapels.Suitable for casual street style, business wear, work office style, leisure time, daily life, vacation, party, etc.",120.10,"1");
//            products product9 = new products("p109","Kids Casual Wear", "kids_clothes"," Stylish and comfortable pajamas two piece set. Solid color  2pcs sleepwear set for children kids girls boys, long sleeve button-down top matching with elastic waist long pants.",50.25,"1");
//
////////
//            product_data.add(product1);
//            product_data.add(product2);
//            product_data.add(product3);
//
//            product_data.add(product4);
//            product_data.add(product5);
//            product_data.add(product6);
//            product_data.add(product7);
//            product_data.add(product8);
//            product_data.add(product9);
//
//
//            database=FirebaseDatabase.getInstance();
//            myRef=database.getReference("Product_data");
//
//    myRef.setValue(product_data).addOnCompleteListener(new OnCompleteListener<Void>() {
//        @Override
//        public void onComplete(@NonNull Task<Void> task) {
//            Log.i("info","Inserted");
//        }
//    });








    }

    public List<products> getProductList() {
        return product_data;
    }

}
