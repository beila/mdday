http://collab.lge.com/main/pages/viewpage.action?pageId=250847383


Introduction
본 문제에서는 샘플 텍스트로 학습한 결과를 바탕으로 랜덤 영문 텍스트를 출력하는 프로그램을 작성한다.

랜덤 출력 결과가 그럴듯한 영문처럼 보이기 위해 Markov Chain Algorithm을 적용한다. 먼저 학습용 텍스트 입력을 통해, 두 단어 Prefix 에 연결된 Suffix 목록을 생성하고, 이를 토대로 랜덤하게 출력하는 방법이다.

예를 들어, "I am not a number! I am a free man!"와 같은 학습용 입력(sample.txt)이 주어지면, 아래와 같은 Prefix-Suffix 맵을 생성할 수 있다. "I am"의 경우에는 뒤에 "not"이나 "a"가 올 수 있으므로 매번 다른 결과물을 얻을 수 있다. 이때 편의상 맨 첫 두 단어의 Prefix 는 고정시켜 둔다. 마찬가지로 맨 마지막 두 단어에 연결된 Suffix 는 없다.


Prefix

Suffix

(공백) (공백) I
(공백) I am
I am not, a
am not a
not a number!
a number! I
number! I am
am a free
a free man!
free man! (공백)

위와 같은 입력으로 아래와 같은 다양한 출력을 얻을 수 있다.
•I am a free man!
•I am not a number! I am a free man!
•I am not a number! I am not a number! I am not a number! I am not a number! I am not a number! I am not a number! I am not a number! I am a free man!
•(계속)

정리하자면 텍스트를 출력할 때 다음의 기준으로 시작/끝을 결정한다.
•문장 시작 - (공백)(공백)을 기준으로 시작한다.
•문장 끝 - 최대 출력 개수에 도달했거나 (공백)을 출력하면 종료된다.

Requirements
본 프로그램은 아래와 같은 다섯 개의 메뉴로 동작된다.
1.학습하기
a.학습용 텍스트 파일명을 입력하면 프로그램은 파일을 읽어들여 Prefix - Suffix 맵을 업데이트한다.

2.학습 데이터 조회
a.학습된 내용 (Prefix-Suffix 맵) 을 출력한다.
b.이 때, 연속된 두 단어로 이루어진 Prefix 마다 그 뒤에 나올 수 있는 Suffix 의 목록을 표로 출력한다.

3.학습 데이터 삭제
a.기존 학습된 데이터를 모두 초기화한다.

4.랜덤 텍스트 출력
a.학습된 데이터에 따라, 랜덤하게 텍스트를 출력한다.
b.출력 데이터의 첫 단어는 동일하지만 Prefix-Suffix 맵에서의 출현 빈도에 따라 랜덤하게 다른 문장을 출력한다.

5.종료하기

Sample Run의 참조하여 동일하게 동작할 수 있도록 프로그램을 완성한다. (Sample Run은 Random 객체 생성 시 Seed 값을 0으로 줬을 때 동일한 결과를 보여주도록 되어 있다.)

Sample run
Chain 클래스의 Random 객체 생성시 0으로 Seed를 준 경우 아래의 결과가 나와야 함. (데이터베이스 Prefix-Suffix 출력 순서는 달라질 수 있음)

[[[ Database contains 0 words. ]]]

1. Learn from sample text

2. Print database

3. Reset database

4. Generate random text

5. Exit

Enter menu [1~5]: 1

Enter text filename: sample.txt

[[[ Database contains 10 words. ]]]

1. Learn from sample text

2. Print database

3. Reset database

4. Generate random text

5. Exit

Enter menu [1~5]: 2

Until now I learned following prefix-suffix map.

Prefix    Suffix

a free    man!

am a      free

number! I am

a number! I

"" I      am

am not    a

not a     number!

"" ""     I

free man!

I am      not,a

[[[ Database contains 10 words. ]]]

1. Learn from sample text

2. Print database

3. Reset database

4. Generate random text

5. Exit

Enter menu [1~5]: 4

Here is auto-generated text:

I am not a number! I am not a number! I am not a number! I am not a number! I am

not a number! I am not a number! I am not a number! I am not a number! I am not

a number! I am a free man!

[[[ Database contains 10 words. ]]]

1. Learn from sample text

2. Print database

3. Reset database

4. Generate random text

5. Exit

Enter menu [1~5]: 4

Here is auto-generated text:

I am a free man!

[[[ Database contains 10 words. ]]]

1. Learn from sample text

2. Print database

3. Reset database

4. Generate random text

5. Exit

Enter menu [1~5]: 4

Here is auto-generated text:

I am not a number! I am a free man!

[[[ Database contains 10 words. ]]]

1. Learn from sample text

2. Print database

3. Reset database

4. Generate random text

5. Exit

Enter menu [1~5]: 3

Clean up database...

[[[ Database contains 0 words. ]]]

1. Learn from sample text

2. Print database

3. Reset database

4. Generate random text

5. Exit

Enter menu [1~5]: 1

Enter text filename: oldman.txt

[[[ Database contains 26584 words. ]]]

1. Learn from sample text

2. Print database

3. Reset database

4. Generate random text

5. Exit

Enter menu [1~5]: 4

Here is auto-generated text:

He was too early for that. He just felt a sudden lurch by the sun. Then he

added, “Blessed Virgin, pray for the next line. Now he could feel the steady

hard pull of the Sacred Heart of Jesus and another rose and it moved on the

coils of line around my toe to wake me. But today is eighty-five days and he

carried the mast with the furled sail on his hands. “Keep my head clear,” he

said aloud. “I went out and out of water in his mouth. It was there for all

fish. He clubbed at heads

[[[ Database contains 26584 words. ]]]

1. Learn from sample text

2. Print database

3. Reset database

4. Generate random text

5. Exit

Enter menu [1~5]: 4

Here is auto-generated text:

He was a fish it was,” the proprietor said. “There has never been such a strong

full-blooded fish,” he said. “But I fear the Indians of Cleveland.” “Have faith

in the meat of the negro down and the water and the bettors went in through its

open door. The old man was dreaming about the great John J. McGraw.” He said

Jota for J. “He used to come to the eastward, he thought. Yet they are crisped

like claws. They were not the ordinary pyramid-shaped teeth of most sharks. They

were fresh and hard on the Terrace and asked for

[[[ Database contains 26584 words. ]]]

1. Learn from sample text

2. Print database

3. Reset database

4. Generate random text

5. Exit

Enter menu [1~5]: 5

Bye.

모범답안 Markov.zip

배포 베이스코드 Markov-base-0.2.zip (java)



.