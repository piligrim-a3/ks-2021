### Задача 🧘️ Reflection API

В данном проекте приведен пример программы сканирующей пакеты проекта с целью обнаружения
классов с нужной аннотацией. Использован пакет [org.reflections][1] 
[добавленный](pom.xml) в зависимости проекта. Создана аннотация 
[ExampleAnnotation][2], которой проаннотированы классы пакета [example][3]. В классе
[AnnotationScanner][4] представлен статический метод **scan** обнаруживающий классы с аннотацией
[ExampleAnnotation][2] и выводящий данные по ним.

### Задание
Дополнить программу функцией [загрузки][5] свойств из [файла](src/main/resources/config.properties)
конфигурации и установки данных свойств в качестве значения статических полей с аннотацией
[InjectProperty][6]. Для реализации загрузчика оставлен класс [AnnotationPropertyInjector][7].
В классе [ClassWithAnnotatedFields][8] представлен пример аннотированных полей.

#### Суть
Пишем программу, которая умеет при старте брать файл с настройками и расставлять значения
конфигурации по статическим полям в проекте.

#### Комментарии
* [InjectProperty][6] имеет свойство **name**, из файла конфигурации загружается свойство с
именем указанном в данном поле (в примере поле **simpleName** и свойство **loadName**).
* Если имя в аннотации не указано для загрузки используется имя переменной (в примере поле **size**).
* Тип свойства сводится по типу аннотированного поля, необходимо реализовать строки,
  целые и вещественные числа, а также коллекции этих типов. 

[1]: https://github.com/ronmamo/reflections
[2]: src/main/java/ru/bgpu/task/annotation/annotations/ExampleAnnotation.java
[3]: src/main/java/ru/bgpu/task/annotation/example
[4]: src/main/java/ru/bgpu/task/annotation/AnnotationScanner.java
[5]: https://www.google.com/search?&q=java+properties+example
[6]: src/main/java/ru/bgpu/task/annotation/annotations/InjectProperty.java
[7]: src/main/java/ru/bgpu/task/annotation/AnnotationPropertyInjector.java
[8]: src/main/java/ru/bgpu/task/annotation/example/ClassWithAnnotatedFields.java

