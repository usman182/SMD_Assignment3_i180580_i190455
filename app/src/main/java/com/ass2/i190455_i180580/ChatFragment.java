package com.ass2.i190455_i180580;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    MsgrDbHelper helper;
    SQLiteDatabase db;

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
    public void onStart() {
        super.onStart();
        helper=new MsgrDbHelper(getContext());
        db=helper.getReadableDatabase();
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
        View view=inflater.inflate(R.layout.fragment_chat, container, false);

        contacts_list=view.findViewById(R.id.contacts_list);
//        my_contacts=getData();


        // Inflate the layout for this fragment
        return view;
    }

    protected List<Contact> getData(){
        List <Contact> ls=new ArrayList<>();
        Contact temp;
        String a,b,cc;
        String[] cols={MsgrContracts.MyContacts.DISPLAY_NAME,MsgrContracts.MyContacts.EMAIL,MsgrContracts.MyContacts.DISPLAY_PIC};
        Cursor c=db.query(MsgrContracts.MyContacts.TABLE_NAME,cols,null,null,null,null,
                MsgrContracts.MyContacts.DISPLAY_NAME+" ASC");

        while(c.moveToNext()){
            a=(c.getString(c.getColumnIndexOrThrow(cols[0])));
            b=(c.getString(c.getColumnIndexOrThrow(cols[1])));
            cc=(c.getString(c.getColumnIndexOrThrow(cols[2])));
            temp=new Contact(a,b);
            temp.setDp(cc);
            ls.add(temp);
        }
        c.close();
        return ls;
    }
}