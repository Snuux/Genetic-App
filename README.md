# Genetic App
Программа, которая осуществляет поиск кратчайшего пути для информационного пакета (сообщения) в компьютерной сети с помощью генетических алгоритмов.

Возможности:
-	Возможность задания топологии сети с указанием ее размерности и пропускной способностью каналов.
-	Настройки работы генетического алгоритма: размер популяции, количество поколений, варианты кроссовера, вероятность мутации и др.
-	Указание исходных данных (компьютер-отправитель и компьютер-получатель) и автоматическое заполнение исходных данные топологии сети.
-	Два режима работы:
--	пошаговый - на экране должны отображаться все представители (хромосомы) одного поколения до и после применения каждого оператора (скрещивания, селекции, редукции и мутации).
--	циклический - на экране должны отражаться только агрегированные данные по каждому поколению и итоговый набор хромосом.

Интерфейс написан с помощью Google Web Toolkit (GWT).
Скрин интерфейса:
![http://i.imgur.com/s9JhUx6.png](http://i.imgur.com/s9JhUx6.png)

Основа программы состоит из 5 классов:
- «GeneticEngine» – основной класс, который хранит хромосомы, и управляет генетическим алгоритмом. Хранит поля настройки генетического алгоритма.
- «Chromosomal» – интерфейс, который определяет свойства хромосомы.
- «SimpleChrormosome» – класс, имплементирующий свойства хромосомы.
- «TspChromosome» – класс, имплементирующий свойства хромосомы (для задачи коммивояжёра).
- «GAAdditions» – класс с дополнительными функциями.

Классы для реализации интерфейса:
«GAUI» – основной класс, агрегирует в себя остальные классы. Имеет вид LayouTabPanel для отображения вкладок:
- «Базовая настройка».
- «Настройка первой генерации».
- «Настройка матрицы смежности».
- «Автоматическое выполнение».
- «Пошаговое выполнение». 
