# Project Data aggregation
[![Build Status](https://travis-ci.com/VitaliyNasypov/data_aggregation.svg?branch=master)](https://travis-ci.com/VitaliyNasypov/data_aggregation)
[![codecov](https://codecov.io/gh/VitaliyNasypov/data_aggregation/branch/master/graph/badge.svg?token=1LIJ06KLS6)](https://codecov.io/gh/VitaliyNasypov/data_aggregation)
<br>
Учебное тестовое задание для экзамена по многопоточности: Необходимо было написать код для получения и агрегации данных из нескольких сервисов. При написании кода учитывалось потенциально большие объемы данных, то есть сбор и агрегация должны выполняться в несколько потоков и как можно меньше блокироваться.
<br>
1. Делаем запрос на получение списка доступных видеокамер по ссылке. В ответ получаем JSON массив объектов.
2. Данные о каждой видеокамере находятся на двух разных ресурсах.
3. Необходимо сагрегировать данные по каждой камере в один JSON массив объектов.
4. Использовался CompletableFuture и Executors.newCachedThreadPool(). Каждая видеокамера обрабатывалась в отдельном потоке. После получения всех данных собирались в JSON массив.
