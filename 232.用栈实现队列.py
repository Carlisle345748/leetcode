class MyQueue:
    """
    方法1，相对于方法2效率更高。每当有出队需求的时候，才把push栈中的元素转移到pop栈，
    当pop栈的元素都用完时，若再有出队需求，才再把push栈的元素转移到pop栈。
    """
    def __init__(self):
        """
        Initialize your data structure here.
        使用一个辅助栈，一共2个栈，一个栈用于push，一个栈用于pop。
        """
        self.pushStack = []
        self.popStack = []

    def push(self, x: int) -> None:
        """
        Push element x to the back of queue.
        push的时候，直接把元素推入push栈
        """
        self.pushStack.append(x)


    def pop(self) -> int:
        """
        Removes the element from in front of queue and returns that element.
        要pop的时候，如果pop栈是空的，要把push栈的元素依次推入pop栈，实现顺序逆转，然后
        从pop栈中pop即可获得push栈的第一个元素。如果pop栈不为空，即之前从push栈转移到pop
        栈的元素还有剩下的，就直接从pop栈里面pop。
        """
        if len(self.popStack) == 0:
            while (len(self.pushStack) != 0):
                self.popStack.append(self.pushStack.pop())
        return self.popStack.pop()

    def peek(self) -> int:
        """
        Get the front element.
        与pop同理
        """
        if len(self.popStack) == 0:
            while (len(self.pushStack) != 0):
                self.popStack.append(self.pushStack.pop())
        return self.popStack[-1]

    def empty(self) -> bool:
        """
        Returns whether the queue is empty.
        检查2个栈是否为空
        """
        return len(self.popStack) == 0 and len(self.pushStack) == 0


class MyQueue:
    """
    第二个方法：每当有入队需求时，把元素都放到push栈，然后入队。有出队需求时，把
    元素都转移到pop栈，然后出队
    """
    def __init__(self):
        """
        Initialize your data structure here.
        使用一个辅助栈，一共2个栈，一个栈用于push，一个栈用于pop。
        """
        self.pushStack = []
        self.popStack = []

    def push(self, x: int) -> None:
        """
        Push element x to the back of queue.
        在push之前，把pop栈的元素都推入push栈，维持栈的push顺序
        """
        while (len(self.popStack) != 0):
            self.pushStack.append(self.popStack.pop())
        self.pushStack.append(x)


    def pop(self) -> int:
        """
        Removes the element from in front of queue and returns that element.
        在pop之前，把push栈的元素都推入pop栈，顺序逆转，从而可以推出第一个元素
        """
        while (len(self.pushStack) != 0):
            self.popStack.append(self.pushStack.pop())
        return self.popStack.pop()

    def peek(self) -> int:
        """
        Get the front element.
        与pop同理
        """
        while (len(self.pushStack) != 0):
            self.popStack.append(self.pushStack.pop())
        return self.popStack[-1]

    def empty(self) -> bool:
        """
        Returns whether the queue is empty.
        判断两个栈是否为空
        """
        return len(self.popStack) == 0 and len(self.pushStack) == 0