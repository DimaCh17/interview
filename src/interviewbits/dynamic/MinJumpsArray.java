package interviewbits.dynamic;

import java.util.*;

public class MinJumpsArray {
    public boolean showOutput = false;
    private void print(String format, Object ... args) {
        if (!showOutput) return;
        System.out.print(String.format(format, args));
    }
    public int jump(ArrayList<Integer> list) {
        if (list == null || list.size() == 0) {
            return NOT_FOUND;
        }
        if (list.size() == 1) {
            return 0;
        }
        final int size = list.size();
        ArrayList<Integer> path = new ArrayList<Integer>(size);
        for (int i = 0; i < size; i++) {
            path.add(NOT_FOUND);
        }
        path.set(0, 0);// important to init path to the first node
        Deque<Integer> dq = new LinkedList<>();
        Deque<Integer> sourceDQ = new LinkedList<>();
        Map<Integer, Integer> distances = new HashMap<>();
        Set<Integer> visits = new HashSet<>();
        dq.addLast(0);
        distances.put(0, 0);// we can reach the zero element in no time
        sourceDQ.addLast(0);
        while (!dq.isEmpty()) {
            int idx = dq.removeLast();
            int sourceIdx = sourceDQ.removeLast();
            if (idx >= size - 1) {// jump over, check for the new distance
                idx = size - 1;
            }
            int srcDistance = distances.get(sourceIdx);
            int hop = 1;// the actual, not maximum distance - LL careful
            // with what the distance is, as in this case it's amount of hops
            final int distance = srcDistance + hop;
            if (!distances.containsKey(idx)) {
                // the new path
                distances.put(idx, distance);
                path.set(idx, sourceIdx);
                print("%d-%d N d=%d\n", sourceIdx, idx, distance);
            } else {
                int oldDistance = distances.get(idx);
                if (distance < oldDistance) {
                    distances.put(idx, distance);
                    path.set(idx, sourceIdx);
                    print("%d-%d U d=%d\n", sourceIdx, idx, distance);
                }
            }
            if (!visits.contains(idx)) {// blocking visits causes bugs{
                //print("visit children of %d\n", idx);
                for (int i = 1; i <= list.get(idx); i++) { // (LL) use inclusive for distances
                    int idxToExplore = idx + i;
                    if (idxToExplore < size) {
                        // as a single node can be found through different paths.
                        // however, if this node has been processes as a source
                        // no need to use it as a source again.
                        sourceDQ.addLast(idx);
                        dq.addLast(idxToExplore);
                        print("%d-%d \n", idx, idxToExplore);
                    }
                }
                visits.add(idx);
            }
        }
        if (!distances.containsKey(size - 1)) {
            return NOT_FOUND;
        }
        int curIdx = path.get(size - 1);
        int steps = 1;
        while (curIdx != 0) {
            steps++;
            curIdx = path.get(curIdx);
        }
        return steps;
    }
    private int NOT_FOUND = -1;
}
