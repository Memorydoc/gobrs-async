

## ��Դ����
- [���ٿ�ʼ](https://async.sizegang.cn/pages/793dcb/#%E5%A6%82%E4%BD%95%E8%BF%90%E8%A1%8C-demo)
- [�ĵ��б�](https://async.sizegang.cn/pages/52d5c3/)
- [��Ŀ����](https://async.sizegang.cn/pages/2f674a/)
- [��Ⱥ��ͨ](https://async.sizegang.cn/pages/dd137d/)
## �������ʲô

[**Gobrs-Async**](https://github.com/Memorydoc/gobrs-async) ��һ���ǿ������������ȫ��·�쳣�ص����ڴ��Ż����쳣״̬������һ��ĸ������첽���ſ�ܡ�Ϊ��ҵ�ṩ�ڸ���Ӧ�ó����¶�̬������ŵ�������
����ڸ��ӳ����£��첽�̸߳����ԡ����������ԡ��쳣״̬�ѿ����ԣ� **Gobrs-Async** Ϊ�˶�����

## �ܽ��ʲô����
�ܽ�� `CompletableFuture` �����ܽ�������⡣ ��ô����أ�

��ͳ��`Future`��`CompleteableFuture`һ���̶��Ͽ������������ţ������԰ѽ�����ݵ���һ��������CompletableFuture��then����������ȴ�޷�������ÿһ��ִ�е�Ԫ�Ļص���Ʃ��Aִ����ϳɹ��ˣ�������B����ϣ��A��ִ�������и��ص�����������Ҽ�ص�ǰ��ִ��״�������ߴ����־ʲô�ġ�ʧ���ˣ���Ҳ���Լ�¼���쳣��Ϣʲô�ġ�

��ʱ��CompleteableFuture������Ϊ���ˡ�

**Gobrs-Async**����ṩ�������Ļص����ܡ����ң����ִ�гɹ���ʧ�ܡ��쳣����ʱ�ȳ����¶��ṩ�˹����߳������������


## ��������
### ����һ
![����һ](https://kevin-cloud-dubbo.oss-cn-beijing.aliyuncs.com/gobrs-async/type1.png)

**˵��**
����A ִ������֮�󣬼���ִ�� B��C��D

### ������

![������](https://kevin-cloud-dubbo.oss-cn-beijing.aliyuncs.com/gobrs-async/type2.png)

**˵��**
����A ִ������֮��ִ��B Ȼ����ִ�� C��D


### ������
![������](https://kevin-cloud-dubbo.oss-cn-beijing.aliyuncs.com/gobrs-async/type3.png)

**˵��**
����A ִ������֮��ִ��B��E Ȼ����˳�� B��������C��D��G�� E��������F��G

> **���и��ೡ�������������ϸ���������ŵĸ�� ����ϸ�Ķ��ĵ�������ͨ����Դ���������������˽�ȫò��**
## Ϊʲôд�����Ŀ

�ڿ���������̨ҵ������У�������������ø�����̨ҵ�����ݣ� ���һ���ָ��ӵ���̨����������ϵ������������¡�����ĸ��ӳ̶Ⱦͻ����ӡ� ����ͼ��ʾ��
![1.1](https://kevin-cloud-dubbo.oss-cn-beijing.aliyuncs.com/oss/1141645973242_.pic.jpg)



�ڵ���ƽ̨ҵ���У� ����̨���ݿ������� ��ƷProduct ���ݣ�������Ҫ�������������� Item�����ݡ��������ѻ��ʣ�ΪʲôProduct ���ݲ��� Item���ݳ���ͬһ����̨�أ���̨ҵ��չ�Ƕ����Եģ���ͬҵ����̨��Ʒ�ʽ��ͬ ��
�ѵ����ǾͲ��Խ�������������Ҫ��������ָ��Ӷ�����̨ҵ�������ṩ����֧�Ų���һ���ϸ�Ŀ�����Ӧ�����ģ�����Item������HTTP�ķ��񣬵�Product ��RPC���� �������Future�� ������ʽ�����ǿ��ܻ���������

```java 

    // ���д������� Product �� Item ������
    @Resource
    List<ParaExector> paraExectors;

    // ������Product �� Item�� ����
    @Resource
    List<SerExector> serExectors;

    public void testFuture(HttpServletRequest httpServletRequest) {
        DataContext dataContext = new DataContext();
        dataContext.setHttpServletRequest(httpServletRequest);
        List<Future> list = new ArrayList<>();
        for (AsyncTask asyncTask : paraExectors) {
            Future<?> submit = gobrsThreadPoolExecutor.submit(() -> {
                asyncTask.task(dataContext, null);
            });
            list.add(submit);
        }
        for (Future future : list) {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        List<Future> ser = new ArrayList<>();
        for (AsyncTask asyncTask : serExectors) {
            Future<?> submit = gobrsThreadPoolExecutor.submit(() -> {
                asyncTask.task(dataContext, null);
            });
            ser.add(submit);
        }
        for (Future future : ser) {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
```

### ���ڵ�����

����ʾ���У�Product������ͨ��RPC ��ʽ��ȡ�� Item��ͨ��HTTP�����ȡ����Ҷ�֪���� RPC����Ҫ����HTTP���ܡ� ����ͨ��Future �ķ�ʽ�� get�������ȴ� Item���ݷ��غ�Ż�����ִ�С� �����Ļ���
ͼ������װ�����ݡ��޹����ݵȶ�Ҫ�ȴ�Item���ݷ��أ�������Щ��̨��������Item���ص����ݣ� ���Ի�����ȴ�ʱ��Ӱ��ϵͳ����QPS��


## ��Դ
![��Դ](https://kevin-cloud-dubbo.oss-cn-beijing.aliyuncs.com/gobrs-async/gobrs-qy.png)

* ����ͨ���Կ�Դ�м����Դ����ϸ�Ķ��Ͷ��ο����ľ����ʹ���ĵ��ܽ������
* �û���һЩʹ������ ����ҵ�������

## Gobrs-Async ��������
![��������](https://kevin-cloud-dubbo.oss-cn-beijing.aliyuncs.com/gobrs-async/gobrs-hxnl.jpg)

## ҵ��Ա�

�ڿ�Դƽ̨����ͦ�������첽���ſ�ܣ����ֶ����Ǻ����룬Ψһһ���ֽ׶αȽϺ��õ��첽���ſ�ܾ���asyncTool�ȽϺ��á�������ʹ��ʱ���֣�API���Ǻܺ��á�������ҪƵ���Ĵ��� <code>WorkerWrapper</code> ����
�������е㲻ˬ������ҵ��Ƚϸ��ӵĳ������ڿ���ʱ��Ҫд�϶�� <code>WorkerWrapper</code> ���룬���ҿ�ܲ��ܶ�ȫ���쳣�������ء�ֻ��ͨ������� <code>result</code> ������׽��������쳣��
������������������쳣�� ֹͣȫ�ֵ��첽����ͬʱ�޷��ڷ���ȫ���쳣��ʱ������쳣���ء������Ҫʵ���ڷ�����ֹͣȫ����������ʱ�򣬷��ͱ����ʼ��Ĺ��ܡ� asyncTool���Ե����������ˡ�

asyncTool �����Ѿ����ܺ�ǿ���ˣ�������asyncTool ���� �����ھ��������з������� �漰���ĳ�����ͬС�졣
���кܶ�ҵ�񳡾����ܸ��ӵ���̨�ӿڵ��ù�ϵ��������Ե�ǰҵ�񳡾�����Ҫ̽������ļ������򡣱�������Ӧ�÷�����ҵ�����ҵ�񳡾���

�����Ŀ��һ�����׼ǵ����֣������� Eureka��Nacos��Redis�������������Ǻ󣬾���������**Gobrs-Async**

| ����|  asyncTool   | Gobrs-Async  | sirector |
|----|  ----  | ----  | ---- |
| �������� | ��  | �� | ��
|  �������쳣�ص�  | ��  | �� | ��
| ȫ���쳣�ж� |��|��| ��
|������������|��|��| ��
|�Զ����쳣������|��|��| ��
|�ڴ��Ż�|��|��| ��
|��ѡ������ִ��|��|��| ��

## �������ʲô����

��������ø�����̨����ʱ���������ֶ����̨���ݻ����������������ʵ�����л��������³�����

���г����ĳ��� 1 �ͻ����������˽ӿڣ��ýӿ���Ҫ��������N��΢����Ľӿ�

Ʃ�� �����ҵĹ��ﳵ����ô����Ҫȥ�����û���rpc����Ʒ�����rpc�����rpc���Ż�ȯ�ȵȺö������ͬʱ����Щ�������໥������ϵ��Ʃ��������õ���Ʒid�󣬲���ȥ���rpc������������Ϣ��
����ȫ����ȡ��Ϻ󣬻�ʱ�ˣ��ͻ��ܽ�������ظ��ͻ��ˡ�

2 ����ִ��N�����񣬺���������1-N�������ִ�н���������Ƿ����ִ����һ������

���û�����ͨ�����䡢�ֻ��š��û�����¼����¼�ӿ�ֻ��һ������ô���û������¼�����������Ҫ���и������䡢�ֻ��š��û�����ͬʱ�����ݿ⣬ֻҪ��һ���ɹ��ˣ�����ɹ����Ϳ��Լ���ִ����һ�������������������ܷ�ɹ��������ֻ��š���

����ĳ�ӿ�������ÿ�����εĴ���������ÿ������ѯ10����Ʒ����Ϣ������45����Ʒ��Ҫ��ѯ���Ϳ��Է�5�Ѳ���ȥ��ѯ����������ͳ����5�ѵĲ�ѯ������Ϳ����Ƿ�ǿ��Ҫ��ȫ����ɹ������ǲ����м��Ѳ�ɹ������ͻ�������

����ĳ���ӿڣ���5��ǰ��������Ҫ����������3���Ǳ���Ҫִ����ϲ���ִ�к����ģ�����2���Ƿ�ǿ�Ƶģ�ֻҪ��3��ִ����Ϳ��Խ�����һ������ʱ����2������ɹ��˾���ֵ�������ûִ���꣬����Ĭ��ֵ��

3 ��Ҫ�����̸߳���Ķ���������

��������� ��������֮��˴˲���أ�ÿ�鶼��Ҫһ���������̳߳أ�ÿ�鶼�Ƕ�����һ��ִ�е�Ԫ����ϡ��е�������hystrix���̳߳ظ�����ԡ�

4 ����������������š�

5 ������˳����ŵ�����

<br/>

## ����ʲô����

Gobrs-Async �ڿ���ʱ�������ڶ�ʹ���ߵĿ���ϲ�������쳣�����ʹ�ó������������õ��������������У��ھ����������Ͽ�ĸ߲������顣ͬʱ�����
�����������á�ȫ���Զ�����ж�ȫ�����쳣���ڴ��Ż������Ľ��뷽ʽ���ṩSpringBoot Start ���뷽ʽ�����ӿ���ʹ���ߵĿ���ϰ�ߡ�����Ҫע��GobrsTask��Spring Bean ����ʵ��ȫ���̽��롣

Gobrs-Async ��ĿĿ¼���侫��

- `gobrs-async-example`��Gobrs-Async ����ʵ�����ṩ����������
- `gobrs-async-starter`��Gobrs-Async ��ܺ������





Gobrs-Async �����ʱ���ͳ�ֿ����˿����ߵ�ʹ��ϰ�ߣ� û�������κ��м���� �Բ�������������õķ�װ����Ҫʹ��
<code>CountDownLatch</code> ��<code>ReentrantLock</code> ��<code>volatile</code> ��һϵ�в�������������ơ�

## ����ܹ�
<br/>

![1.0](https://kevin-cloud-dubbo.oss-cn-beijing.aliyuncs.com/gobrs-async/gobrs-jgt3.png)

## ���񴥷���

�������������ߣ� ������������ִ����

## �����������

�������ʹ�������õĹ���ͬʱ��Spring��ϣ������õ� <code>Spring Bean</code> ������ <code>TaskBean</code>������ͨ������������س� ����װ������������װ��������

## ����������

����ͨ��ʹ�ý����������������������� **JUC** ������ܵ���ʵ�ֶ������ͳһ�������ķ�����
* trigger ���������������Ϊ��������׼������

## ���������
��������������̣���ʼ��������ִ����ִ�к�������

* load �����������̷����������������ȴ�������������
* getBeginProcess ��ȡ������ʼ����
* completed �������
* errorInterrupted ����ʧ�� �ж���������
* error ����ʧ��


### ����ִ����
���յ�����ִ�У�ÿһ�������Ӧһ��<code>TaskActuator</code> ����� ���ء��쳣��ִ�С��̸߳��� �ȱ�Ҫ�����ж϶������ﴦ��
* prepare ����ǰ�ô���
* preInterceptor ͳһ����ǰ�ô���
* task �������񷽷���ҵ��ִ������
* postInterceptor ͳһ���ô���
* onSuccess ����ִ�гɹ��ص�
* onFail ����ִ��ʧ�ܻص�


## ��������
�������̴������ߣ����� �������������������� ��Ӧ����� �ö���¶��ʹ���ߣ��õ�ƥ��ҵ���������Ϣ�����磺 ���ؽ���������ж��������̵ȹ���
��Ҫ��������(<code>TaskSupport</code>)֧��

## ������ͼ
![������ͼ](https://kevin-cloud-dubbo.oss-cn-beijing.aliyuncs.com/gobrs-async/hxlt.jpg)

## ��Ⱥ��ͨ
���������Ŀ���Ƿ���ʲô��һ����������ӭ�� Issue һ��ͨ������
Ⱥ��ά�������ʧЧ�������������΢�Ž�����Ⱥ

<table>
  <tr>
    <td align="center" style="width: 400px;">
      <a href="https://github.com/Memorydoc">
        <img src="https://kevin-cloud-dubbo.oss-cn-beijing.aliyuncs.com/gobrs-async/1261646574221_.pic_hd.jpg?x-oss-process=image/resize,h_500,w_800" style="width: 400px;"><br>
        <sub></sub>
      </a><br>
    </td>
    <td align="center" style="width: 400px;">
      <a href="https://github.com/Memorydoc">
        <img src="https://kevin-cloud-dubbo.oss-cn-beijing.aliyuncs.com/gobrs-async/1251646574128_.pic.jpg?x-oss-process=image/resize,h_500,w_800" style="width: 400px;"><br>
        <sub></sub>
      </a><br>
    </td>
  </tr>
</table>
