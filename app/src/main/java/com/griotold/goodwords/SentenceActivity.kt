package com.griotold.goodwords

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class SentenceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sentence)

        val sentenceList = mutableListOf<String>()

        sentenceList.add("사람에게 하나의 입과 두 개의 귀가 있는 것은 말하기보다 듣기를 두 배로 하라는 뜻이다.")
        sentenceList.add("천번 재고, 한 번 잘라라.")
        sentenceList.add("행복은 언제나 자신의 마음이 정해.")
        sentenceList.add("멈추면 녹슬기 마련.")
        sentenceList.add("거미줄도 모이면 사자를 묶는다.")
        sentenceList.add("모든 건 지나 간다.")
        sentenceList.add("침착하게 앞으로 계속 나아가라.")
        sentenceList.add("어둠을 탓하기보다 촛불을 켜라.")
        sentenceList.add("승자는 눈을 밟아 길을 만들지만 패자는 눈이 녹기를 기다린다.")
        sentenceList.add("두 개의 화살을 갖지 마라. 두 번째 화살이 있기 때문에 첫 번째 화살에 집중하지 않게 된다.")
        sentenceList.add("뛰어난 말에게도 채찍이 필요하다.")

        val adapter = ListViewAdapter(sentenceList)
        val listView = findViewById<ListView>(R.id.sentenceListView)

        listView.adapter = adapter

    }
}