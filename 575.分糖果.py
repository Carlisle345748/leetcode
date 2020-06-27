class Solution:
    def distributeCandies(self, candies: list]) -> int:
        memo = set()
        for i in candies:
            if i not in memo:
                memo.add(i)
            if len(memo) >= len(candies) / 2:
                return len(memo)
        return len(memo)