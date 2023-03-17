num = input("請輸入一個數字：")

try:
    num = int(num)
except:
    print(num, "不是整數！")
    exit()

# 計算
if num % 2 == 1:
    print("X")
elif 2 <= num <= 5:
    print("O")
elif 6 <= num <= 20:
    print("X")
else:
    print("O")
