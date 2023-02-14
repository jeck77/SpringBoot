package com.edu.lifecyle;

//implements InitializingBean, DisposableBean
public class NetworkClient  {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출 , url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect(){
        System.out.println("connect  : " + url);
    }

    public void call(String message){
        System.out.println("call = " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect(){
        System.out.println("close " + url);
    }


    /**
     * implements InitializingBean, DisposableBean 방식
     * 의존 관계 주입이 끝나면 알려준다.
     *
     * 단점 :
     * 1. 이 인터페이스는 스프링 전용 인터페이스다. 해당 코드가 스프링 전용 인터페이스에 의존한다.
     * 2. 초기화, 소멸 메소드 이름을 변경할 수 없다.
     * 3. 내가 고칠 수 없는 외부 라이브러리에 적용할 수없다.
     * 따라서 최근에는 거의 사용하지 않는다.
     */
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메세지");
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }


    /**
     * @Bean(initMethod = "", destoryMethod = "")
     *
     * 장점 :
     * 1. 메소드 이름을 자유롭게 가능
     * 2. 스프링 빈이 스프링 코드에 의존 x
     * 3. 코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용 가능
     *
     * "종료 메서드의 추론"
     * destoryMethod의 Default값은 inferred(추론)으로 등록 되어 있다.
     * 라이브러리의 대부분 close, shutdown 이라는 이름의 종료 메서드를 사용한다.
     * 이 추론의 기능은 close, shutdown라는 이름의 메서드를 자동으로 호출해준다.
     * 따라서 직접 스프링 빈으로 등록하면 종료 메서드를 따로 적어주지 않아도 잘 동작한다.
     * 만약 추론 기능을 사용하기 싫으면 destoryMethod = "" 빈값을 넣어주면 된다.
     */
    public void init() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }

    public void close() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
