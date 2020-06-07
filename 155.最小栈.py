# 设置一个同步辅助栈，记录每次推入一个元素后当前的最小值。推出栈元素的
# 时候同步推出辅助栈的元素，从而保持辅助栈顶一直都是当前栈最小值
class MinStack:

    def __init__(self):
        """
        initialize your data structure here.
        """
        self.data = [None]
        self.min_stack = [inf]

    def push(self, x: int) -> None:
        self.data.append(x)
        if x < self.min_stack[-1]:
            self.min_stack.append(x)
        else:
            self.min_stack.append(self.min_stack[-1])

    def pop(self) -> None:
        self.data.pop()
        self.min_stack.pop()

    def top(self) -> int:
        return self.data[-1]

    def getMin(self) -> int:
        return self.min_stack[-1]

# 设置一个不同步辅助栈，当推入元素小于等于(注意等于)时，往辅助栈
# 内推入这个新的最小值(小于等于上一个最小值)。在推出元素时，如果
# 推出的元素与辅助栈顶的值相同，则辅助栈也推出栈顶的值，从而保持
# 栈顶一直都是当前栈最小值。相比于同步辅助栈，这个方法可以节省空间
class MinStack2:

    def __init__(self):
        """
        initialize your data structure here.
        """
        self.data = []
        self.min_stack = []

    def push(self, x: int) -> None:
        self.data.append(x)
        if len(self.min_stack) == 0 or x <= self.min_stack[-1]:
            self.min_stack.append(x)

    def pop(self) -> None:
        x = self.data.pop()
        if x == self.min_stack[-1]:
            self.min_stack.pop()

    def top(self) -> int:
        return self.data[-1]

    def getMin(self) -> int:
        return self.min_stack[-1]