package com.chunjae.test05.exception;

public class NoSuchDataException extends RuntimeException{
    public NoSuchDataException(String message){
        super(message);
    }
    /*
    컨트롤러에서 getUserList 호출 -> 만약 테이블이 비어있다면 빈 리스트 반환(안해주면 null로 비교할 수 없음)
    getUser: 일치하는 데이터가 없으면 null로 반환
    updateUser: update된 데이터가 없으면 0으로 반환
    removeUser: delete된 데이터가 없으면 0으로 반환
    joinPro: insert된 데이터가 없으면 0반환
     */
}
