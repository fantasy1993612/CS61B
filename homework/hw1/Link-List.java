//1 practice with linked list 
 IntList L = new IntList(4, null);
 L = new IntList(3, L);
 L = new IntList(2, L);
 L = new IntList(1, L);
 IntList M = L.tail;
 IntList N = new IntList(6, null);
 N = new IntList(5, N);

 N.tail.tail = N;
 M.tail.tail.tail = N.tail;
 L.tail.tail = L.tail.tail.tail;
 L = M.tail;