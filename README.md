# 🧪 MVI 테스트용 프로젝트

이 프로젝트는 최근 **Jetpack Compose에는 MVI가 더 자연스럽고 적합하다**는 의견이 많아지는 흐름 속에서,  
과연 그 주장이 항상 맞는지 **직접 구조를 설계하고 테스트해보기 위해** 시작되었습니다.

단순히 MVI를 적용하는 데 그치지 않고,  
MVVM(단방향 흐름 기반)과 비교해 **확장성, 복잡도, 협업 측면에서 어떤 차이가 있는지** 검증하는 것이 핵심 목표입니다.
---

## 🔍 MVI란 무엇인가?

**MVI (Model - View - Intent)**는 UI 상태를 단일 객체(State)로 정의하고,  
사용자의 인터랙션을 명시적인 Event(Intent)로 전달하며,  
이벤트를 처리한 결과를 새로운 State와 일회성 Effect로 분리하는 **단방향 흐름 기반 아키텍처**입니다.

User Action → Event(Intent) → ViewModel → State / Effect → View


---

## 💬 MVVM과 차이? 그리고 애매한 진실
대부분의 MVVM 기반 Android 프로젝트에서도 실제로는  
**양방향 바인딩 없이 단방향 흐름으로 ViewModel을 호출하고**,  
LiveData 또는 StateFlow로 상태를 구독하는 방식이 사용됩니다.

즉, **MVVM이라고 부르지만, 구조적으로는 MVI와 유사한 형태로 쓰고 있는 경우가 많습니다.**

예를 들어 다음과 같은 코드 패턴은 MVI와 매우 유사합니다:

```kotlin
viewModel.onEvent(SomeUiEvent)           // MVI의 Intent와 유사
viewModel.uiState.collectAsState()       // MVI의 State 구독과 동일
```
결국 명시적으로 Intent / State / Effect를 나누는가,
아니면 그걸 ViewModel 내에서 묶어서 MVVM 형태로 쓰는가의 차이입니다.

## ❓ 그래서 어떤 걸 택해야 하나요?
이름을 명확히 구분하고 역할을 나누는 것은 팀 단위 협업이나 유지보수에 있어 명확한 이점을 가집니다.

그러나 MVI는 구조적 명확성의 대가로 보일러플레이트가 증가하고,  
단순한 화면에서는 오히려 과도한 설계가 될 수 있습니다.

👉 결국 중요한 건 **화면의 복잡도와 팀의 합의 수준에 따라 유연하게 선택하는 것**입니다.  
아키텍처는 도구일 뿐, 정답은 없습니다.

