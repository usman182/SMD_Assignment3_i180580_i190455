package com.ass2.i190455_i180580;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {

    RecyclerView contacts_list;
    List<Contact> my_contacts;
    ContactAdapter contact_adapter;
    List<ChatMessage> cs;
    ChatAdapter x;
    RecyclerView.LayoutManager lm;

    FirebaseAuth mAuth;

    FirebaseDatabase rdb;
    DatabaseReference mRef;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_chat, container, false);
        contacts_list=view.findViewById(R.id.contacts_list);

        mAuth=FirebaseAuth.getInstance();

        rdb=FirebaseDatabase.getInstance();
        mRef=rdb.getReference();
        my_contacts=new ArrayList<>();

        Contact x=new Contact("Habib","XYZ","Hey Hassan");
        my_contacts.add(x);

        lm=new LinearLayoutManager(view.getContext());
        contact_adapter=new ContactAdapter(view.getContext(),my_contacts);
        contacts_list.setLayoutManager(lm);
        contacts_list.setAdapter(contact_adapter);

        mRef.child("contacts").child(mAuth.getCurrentUser().getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String name,email,dp;
                name=snapshot.child("display_name").getValue(String.class);
                email=snapshot.child("email").getValue(String.class);
                dp=snapshot.child("uri").getValue(String.class);

                Contact temp=new Contact(name,dp,null);
                temp.setEmail(email);
                my_contacts.add(temp);
                contact_adapter=new ContactAdapter(view.getContext(),my_contacts);
                contacts_list.setAdapter(contact_adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//


        return view;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        contacts_list=view.findViewById(R.id.contacts_list);
//
////        Contact x=new Contact("Habib","XYZ","Hey Hassan");
////        my_contacts.add(x);
//        lm=new LinearLayoutManager(view.getContext());
//        contact_adapter=new ContactAdapter(my_contacts);
//        contacts_list.setLayoutManager(lm);
//        contacts_list.setAdapter(contact_adapter);
//    }
}