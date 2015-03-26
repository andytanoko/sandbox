/**
 *
 */
package az.interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 *
Merge Overlapping Intervals
Given a set of time intervals in any order, merge all overlapping intervals into one and output the result which should have only mutually exclusive intervals.
Let the intervals be represented as pairs of integers for simplicity.
For example, let the given set of intervals be {{1,3}, {2,4}, {5,7}, {6,8} }
. The intervals {1,3} and {2,4} overlap with each other, so they should be merged and become {1, 4}. Similarly {5, 7} and {6, 8} should be merged and become {5, 8}

Write a function which produces the set of merged intervals for the given set of intervals.

A simple approach is to start from the first interval and compare it with all other intervals for overlapping, if it overlaps with any other interval,
 then remove the other interval from list and merge the other into the first interval.
  Repeat the same steps for remaining intervals after first.
  This approach cannot be implemented in better than O(n^2) time.

An efficient approach is to first sort the intervals according to starting time. Once we have the sorted intervals, we can combine all intervals in a linear traversal.
 The idea is, in sorted array of intervals, if interval[i] doesn’t overlap with interval[i-1], then interval[i+1] cannot overlap with interval[i-1]
  because starting time of interval[i+1] must be greater than or equal to interval[i].
  Following is the detailed step by step algorithm.

1. Sort the intervals based on increasing order of starting time.
2. Push the first interval on to a stack.
3. For each interval do the following
……..a. If the current interval does not overlap with the stack top, push it.
……..b. If the current interval overlaps with stack top and ending time of current interval is more than that of stack top, update stack top with the ending time of current interval.
4. At the end stack contains the merged intervals.

 @author Andy Tanoko
 *
 */
public class MergeIntervals {

    public List<Interval> merge(final List<Interval> intervals) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(intervals.size() == 0) {
            return intervals;
        }
        if(intervals.size() == 1) {
            return intervals;
        }

        Collections.sort(intervals, new IntervalComparator());
        printInterval(intervals);
        Interval first = intervals.get(0);
        int start = first.start;
        int end = first.end;

        ArrayList<Interval> result = new ArrayList<Interval>();

        for(int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            if(current.start <= end) {
                end = Math.max(current.end, end);
            } else {
                result.add(new Interval(start, end));
                start = current.start;
                end = current.end;
            }

        }

        result.add(new Interval(start, end));

        return result;

    }

    public void printInterval(final List<Interval> listInterval) {
        for(Interval intv : listInterval) {
            System.out.print(intv.toString());
        }
        System.out.println();
    }

    public static void main(final String[] args) {
        List<Interval> listInterval = new ArrayList<Interval>();

        listInterval.add(new Interval(1, 4));
        listInterval.add(new Interval(3, 5));
        listInterval.add(new Interval(1, 6));
        listInterval.add(new Interval(7, 10));
        listInterval.add(new Interval(7, 8));

        MergeIntervals mi = new MergeIntervals();
        mi.printInterval(listInterval);
        List<Interval> result = mi.merge(listInterval);
        mi.printInterval(result);
    }
}

class Interval {

    int start;
    int end;

    public Interval(final int start, final int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "(" + this.start + "," + this.end + ")";
    }
}

class IntervalComparator implements Comparator<Interval> {

    @Override
    public int compare(final Interval o1, final Interval o2) {

        return o1.start - o2.start;
    }
}