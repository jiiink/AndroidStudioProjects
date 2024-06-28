1. color/purple_200에서 에러 발생 시 강의자료 15p 참고

2. drawer 생성 시 (예제 4,5) 강의자료 29p 참고


3. 예제 4,5번에서 상단에 메뉴 버튼이 나오지 않는 경우
AndroidManifest.xml에 android:theme을
__________________________________________________________________
android:theme="@style/Theme.AppCompat.Light.DarkActionBar"
__________________________________________________________________
로 교체 (강의자료 30p)

4. gradle에서
__________________________________________________________________
buildFeatures{
        viewBinding = true
        dataBinding = true
    }
__________________________________________________________________
implementation("androidx.recyclerview:recyclerview-selection:1.1.0")
__________________________________________________________________
추가 (강의자료 31p)

5. skeleton 코드 넣을 때 패키지 및 import 명 본인 프로젝트명과 일치시키기