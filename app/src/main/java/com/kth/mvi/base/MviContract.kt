package com.kth.mvi.base

interface MviContract {
    interface State   // UI 상태
    interface Event   // 사용자 액션에 대한 이벤트
    interface Effect  // 일회성 UI 이벤트
}