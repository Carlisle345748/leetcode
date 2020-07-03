class Solution:
    def robotSim(self, commands: list, obstacles: list) -> int:
        x, y = 0, 0
        direction = 0 # 0:北 1:东 2:南 3:西
        maxDis = 0
        # 将obstacles转化为set，节省查询时间
        obstacles = set(map(tuple, obstacles))
        # 不同方向时，每走一步x和y的变化
        dx = [0, 1, 0, -1]
        dy = [1, 0, -1, 0]
        for step in commands:
            if step == -1:
                direction += 1
                direction %= 4
            elif step == -2:
                direction -= 1
                direction %= 4
            else:
                for i in range(step):
                    # 如果下一步的位置不是障碍物，就更新位置
                    if (x + dx[direction] , y + dy[direction]) not in obstacles:
                        x, y = x+ dx[direction], y + dy[direction]
                        maxDis = max(maxDis, x**2 + y**2)
        return maxDis


s1 = Solution()
print(s1.robotSim([4,-1,4,-2,4], [[2,4]]))