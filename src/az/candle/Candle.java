/**
 *
 */
package az.candle;

import java.util.ArrayList;

public class Candle {
    public static void main(final String str[]){
        int[] a = {10, 20, 30};
        int n = 3;
        //calling function calcDays which returns the number of days in an ArrayList first index
        int days = (calcDays(0, a, n, 1, 0, n + 1)).get(0);
        System.out.println("Days: " + days);
    }

    private static ArrayList<Integer> calcDays(final int start, final int[] a, final int n, int day, final int cnt, final int dayCnt) {
        int days = 0;
        int temp = 0;
        int count = 0;
        boolean flag = false;
        ArrayList<Integer> list = new ArrayList<Integer>();

        if (start < n){
            //day being the nth day
            here :while (day < dayCnt){
                //count being the number of candles
                count = cnt;
                for (int x = start; x < n ; x ++ ){
                    if (a[x] >= 2){
                        a[x]--;
                        count ++;
                        temp = x;
                        System.out.print("a[" + x + "]::");
                    }else{

                        /*recursively calling this function
                         * which returns number of days and count of candles
                         */
                        count += (calcDays(x + 1, a, n, day, count, day + 1)).get(1);
                    }
                    if (count == day){
                        if (start == 0) {
                            System.out.println("Day " + day);
                        }
                        days += 1;
                        break;
                    }else if (count > n){
                        break here;
                    }
                }

                if (count < day){
                    for (int y = (temp + 1) == n ? 0:(temp + 1); y < n ; y ++ ){
                        if(a[y] > 0){
                            a[y]--;
                            count ++;
                            System.out.print("a[" + y + "]::");
                            if (count == day){
                                days += 1;
                                break;
                            }
                        } else if(!flag && y == n - 1) {
                            //If end of row is reached, start from the beginning
                            flag = true;
                            y = -1;
                        }
                    }
                }
                day ++;
            }
        }

        list = new ArrayList();
        list.add(0, days);

        if(flag && count < day - 1) {
            System.out.println("NOT PROCESSED as number of candles are less than the day count");
            list.add(1, n + 1);
        }else{
            list.add(1, count);
        }

        return list;
    }

}