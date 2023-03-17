package com.ntukhpi.otp.momot.fourth_course_dma.lab5;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.ntukhpi.otp.momot.fourth_course_dma.Menu;
import com.ntukhpi.otp.momot.fourth_course_dma.lab1.R;
import com.ntukhpi.otp.momot.fourth_course_dma.lab5.list.ListElement;
import com.ntukhpi.otp.momot.fourth_course_dma.lab5.list.ListAdapter;
import com.ntukhpi.otp.momot.fourth_course_dma.lab5.navbar.NavbarAdapter;
import com.ntukhpi.otp.momot.fourth_course_dma.lab5.navbar.NavbarModel;
import com.ntukhpi.otp.momot.fourth_course_dma.lab5.util.GenerateString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Random;

public class MainActivity extends Menu {
    private static final int LIST_SIZE = 25;
    private static final int WORD_LENGTH = 5;
    private DrawerLayout drawerLayout;
    private ListView navbarList;
    private ListView elementsList;
    private Toolbar toolbar;
    private ActionBarDrawerToggle barDrawerToggle;
    private final ArrayList<ListElement> elements = new ArrayList<>(LIST_SIZE);
    private FloatingActionButton actionButton;
    private ArrayAdapter<ListElement> listAdapter;

    private final int[] listImagesId = {R.drawable.lab5_1, R.drawable.lab5_2, R.drawable.lab5_3,
            R.drawable.lab5_4, R.drawable.lab5_5, R.drawable.lab5_6,
            R.drawable.lab5_7, R.drawable.lab5_8};
    private final String[] navbarText = {"Up", "Down", "Sort 1", "Sort 2"};
    private final int[] icons = {R.drawable.lab5_up, R.drawable.lab5_down,
            R.drawable.lab5_sort_natural, R.drawable.lab5_sort_reverse};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab5_activity_main);

        findElements();

        setupToolbar();
        setupDrawerToggle();
        setupNavbar();
        setupFloatingButton();
        setupListAdapter();

        drawerLayout.addDrawerListener(barDrawerToggle);

        generateData();
    }

    private void findElements() {
        drawerLayout = findViewById(R.id.lab5_drawer_layout);
        navbarList = findViewById(R.id.lab5_elements);
        elementsList = findViewById(R.id.lab5_elements_list);
        actionButton = findViewById(R.id.lab5_floating_action_button);
        toolbar = findViewById(R.id.lab5_toolbar);
    }

    private void setupListAdapter() {
        listAdapter = new ListAdapter(this, R.layout.lab5_list_item, elements);
        elementsList.setAdapter(listAdapter);
    }

    private void setupFloatingButton() {
        actionButton.setOnClickListener(view -> actionA());
    }

    private void setupNavbar() {
        NavbarModel[] drawerItem = new NavbarModel[4];

        for (int i = 0; i < 4; i++) {
            drawerItem[i] = new NavbarModel(icons[i], navbarText[i]);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        ArrayAdapter<NavbarModel> adapter = new NavbarAdapter(this, R.layout.lab5_navigation_drawer_row, drawerItem);
        navbarList.setAdapter(adapter);
        navbarList.setOnItemClickListener((parent, view, position, id) -> selectItem(position));
    }

    private void selectItem(int position) {
        switch (position) {
            case 0:
                actionB(true);
                break;

            case 1:
                actionB(false);
                break;

            case 2:
                actionC(true);
                break;

            case 3:
                actionC(false);
                break;

            default:
                break;
        }

        drawerLayout.closeDrawer(navbarList);
    }

    private void actionA() {
        ListElement elementToRemove = listAdapter.getItem(0);
        listAdapter.remove(elementToRemove);
        listAdapter.add(generateNewElement());
        listAdapter.notifyDataSetChanged();
    }

    private void actionB(boolean first) {
        ListIterator<ListElement> iterator;
        ListElement temp = null;

        if (first) {
            iterator = ((ListAdapter) listAdapter).getAll().listIterator();
            while (iterator.hasNext()) {
                temp = iterator.next();
                if (checkWordIsVowel(temp.getText1()) && checkWordIsVowel(temp.getText2())) {
                    break;
                }
            }
        } else {
            iterator = ((ListAdapter) listAdapter).getAll().listIterator(((ListAdapter) listAdapter).getAll().size());
            while (iterator.hasPrevious()) {
                temp = iterator.previous();
                if (checkWordIsVowel(temp.getText1()) && checkWordIsVowel(temp.getText2())) {
                    break;
                }
            }
        }

        elementsList.smoothScrollToPosition(((ListAdapter) listAdapter).indexOf(temp));
    }

    private boolean checkWordIsVowel(String word) {
        return isVowel(word.charAt(word.length() - 1));
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'y' ||
                ch == 'u' || ch == 'i' || ch == 'o';
    }

    private void actionC(boolean order) {
        Comparator<ListElement> comparator = order ? Comparator.naturalOrder() : Comparator.reverseOrder();
        ((ListAdapter) listAdapter).getAll().sort(comparator);
        listAdapter.notifyDataSetChanged();
    }

    private ListElement generateNewElement() {
        return new ListElement(GenerateString.getText(WORD_LENGTH),
                GenerateString.getText(WORD_LENGTH),
                listImagesId[new Random().nextInt(listImagesId.length)]);
    }

    private void generateData() {
        for (int i = 0; i < LIST_SIZE; i++) {
            elements.add(generateNewElement());
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        barDrawerToggle.syncState();
    }

    void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle() {
        barDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.app_name,
                R.string.app_name);
        barDrawerToggle.syncState();
    }
}