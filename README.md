# 명언 앱
---

## 소개
![메인화면](https://github.com/user-attachments/assets/8b9b051c-f685-44b2-9465-9fc8bec66b56)

- 데이터베이스(하드코딩)에 등록된 명언을 랜덤으로 보여주는 앱입니다.

![전체보기명언버튼눌렀을때](https://github.com/user-attachments/assets/7d0f5e43-063a-4320-8086-dfd3fca36316)

- 전체명언보기 버튼을 누르면 데이터베이스(하드코딩)에 등록된 전체 명언을 확인할 수 잇습니다.

---

## 주요 기능

- 명언 출력
- ListView 와 Adapter를 활용하여 전체 명언 나열
- 커스텀 폰트 적용 - 배달의 민족 주아체
- 뒤로가기 버튼 클릭시 알림 출력

![뒤로가기버튼클릭시](https://github.com/user-attachments/assets/ac75edfe-357d-4daf-bba8-2e1f89c7da94)

---

## 앱 화면

- **메인화면:** 화면 중앙에 명언이 출력되고, 우측 상단에 "전체명언보기" 버튼이 노출됩니다.
- **전체명언화면:** 전체 명언 리스트가 출력됩니다.

---

## 사용 기술

- **언어:** Kotlin
- **플랫폼:** Android
- **UI:** ㅣLinearLayout, ListView
- **기능:** Toast, Intent, Activity 전환

---

## 기술 적용

### 커스텀 폰트 적용하기

1. 배달의 민족 - 주아체 다운로드
- https://gongu.copyright.or.kr/gongu/wrt/wrt/view.do?wrtSn=13288252&menuNo=200023

2. res > font 에 ttf 파일 넣기

![image](https://github.com/user-attachments/assets/e477d63e-04f1-423c-a8af-1e2424b68e78)

- 다운로드 받으면 BMJUA_ttf.ttf 로 되어 있는데, 사진처럼 소문자로 변경해야 한다.

3. 레이아웃에서 폰트 적용

```
<TextView
            android:id="@+id/goodWordTextArea"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="명언이 들어갈 위치입니다!"
            android:fontFamily="@font/bmjua_ttf"  // 여기!
            android:layout_margin="20dp"
            android:gravity="center"
            android:textColor="@color/white"
            android:textSize="30sp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

```

### ListView와 Adapter
![전체보기명언버튼눌렀을때](https://github.com/user-attachments/assets/44e6572a-c82b-4bc1-bda1-e58ea114e73a)

1. 커스텀 어댑터 구현
```kotlin
class ListViewAdapter(val list: MutableList<String>) : BaseAdapter() {
    override fun getCount(): Int = list.size
    override fun getItem(position: Int): Any = list[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.listview_item, parent, false)
        }
        val textView = view?.findViewById<TextView>(R.id.ListViewTextArea)
        textView!!.text = list[position]
        return view!!
    }
}

```

2. SentenceActivity에 명언 리스트 생성
```kotlin
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
        // 이하 생략
```


### 뒤로가기 버튼 이벤트 구현

![뒤로가기버튼클릭시](https://github.com/user-attachments/assets/11dbc3a9-b6ad-47a1-a578-e437e13a1c80)

- 뒤로가기 버튼을 클릭하면 "한 번 더 누르면 종료합니다" 라는 Toast를 출력

1. 뒤로가기 콜백 등록
```kotlin
package com.griotold.goodwords

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.griotold.goodwords.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {

        ...

        // 뒤로가기 콜백 등록
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (System.currentTimeMillis() - backPressedTime <= 2000) {
                    finish()
                } else {
                    backPressedTime = System.currentTimeMillis()
                    Toast.makeText(this@MainActivity, "한 번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
```


---

## 실행 방법

1. 레포지토리를 클론한 후 Android Studio에서 엽니다.
2. 실제 디바이스 또는 에뮬레이터에서 실행합니다.
3. 메인 화면에서 이미지를 클릭하면 Toast 메시지가 뜨고,  
   클릭한 이미지를 크게 볼 수 있는 화면으로 이동합니다.

---


# References
[왕초보편] 앱 8개를 만들면서 배우는 안드로이드 코틀린(Android Kotlin)
https://www.inflearn.com/course/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%BD%94%ED%8B%80%EB%A6%B0-%EB%AA%A8%EB%B0%94%EC%9D%BC%EC%95%B1
