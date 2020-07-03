class Solution1:
    """
    直接模拟题目描述的三个迁移操作
    """
    def shiftGrid(self, grid: list, k: int) -> list:
        for n in range(k):
            last = grid[-1][-1]
            for i in range(len(grid)-1, -1, -1):
                for j in range(len(grid[0]) - 1, 0, -1):
                    grid[i][j] = grid[i][j-1]
                grid[i][0] = grid[i-1][-1] if i != 0 else last
        return grid

class Solution2:
    """
    观测可得，迁移操作实际上是把数组所有元素向后移动一位，最后一个元素移动到数组开头
    因此可以直接简化迁移逻辑。
    """
    def shiftGrid(self, grid: list, k: int) -> list:
        for n in range(k):
            prev = grid[-1][-1]
            for i in range(len(grid)):
                for j in range(len(grid[0])):
                    temp = grid[i][j]
                    grid[i][j], prev = prev, grid[i][j]
        return grid

class Solution3:
    """
    为了节省时间，构建一个新空白数组，直接使用取模匀运算来计算k次迁移后元素的位置，然后填入空白数组
    最后返回新数组作为答案
    """
    def shiftGrid(self, grid: list, k: int) -> list:
        nrow, ncol = len(grid), len(grid[0])
        # 注意这里用了列表生成器构造嵌套数组而不直接*nrow，是因为用*生成的嵌套数组中
        # 每个子数组都会引用同一个数组，因此修改newgird[0][1]会影响newgrid[1][1]，
        # 而列表生成器会构建不同的数组对象，每个子数组互相独立，不会相互影响
        newgrid = [ncol * [0] for i in range(nrow)]  
        for i in range(nrow):
            for j in range(ncol):
                # 根据i和j计算二维数组的顺序index
                index = i * ncol + j
                # 迁移了k次，向后移动了k位
                index += k
                # 取模运算获得迁移k次后的顺序index
                index %= (nrow*ncol)
                # 通过顺序index计算row和col
                row = index // ncol
                col = index % ncol
                # 写入新数组
                newgrid[row][col] = grid[i][j]
        return newgrid

test = [[1,2,3], [4,5,6], [7,8,9]]
s3 = Solution3()
print(s3.shiftGrid(test, 1))