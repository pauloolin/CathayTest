text = "Hello welcome to Cathay 60th year anniversary"

# 字母轉換為小寫
text = text.lower()

# 儲每個字母的出現次數
letter_count = {}

# 統計出現次數
for letter in text:
    if letter != ' ':
        if letter in letter_count:  # 如果已經統計過這個字母
            letter_count[letter] += 1
        else:  # 如果是第一次統計這個字母
            letter_count[letter] = 1

# 排序
sorted_letters = sorted(letter_count.keys())


# 按照字母順序印出
for letter in sorted_letters:
    count = letter_count[letter]
    print(letter, " ", count)
