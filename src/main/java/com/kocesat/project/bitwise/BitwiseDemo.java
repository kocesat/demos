package com.kocesat.project.bitwise;

public class BitwiseDemo {

  public static final byte READ = (byte)1;    // 001
  public static final byte WRITE = (byte)2;   // 010
  public static final byte EXECUTE = (byte)4; // 100


  public static void main(String[] args) {
    // BITWISE AND
    byte permission = (byte) 3;  // ( 0 1 1 ) => read write (not execute)
    resolve(permission);

    // BITWISE OR
//    permission = (byte) (permission | EXECUTE);
    permission |= EXECUTE;
    resolve(permission);

    // BITWISE XOR
    permission ^= EXECUTE;
    resolve(permission);

    // BITWISE COMPLEMENT
    byte x = (byte) 10;
    byte complementX = (byte) ~x;
    System.out.println(complementX);

    // BITWISE RIGHT SHIFT (>>)
    byte y = (byte) 12;
    byte rightShiftedY = (byte)(y >> 2);
    System.out.println(rightShiftedY);

    // BITWISE LEFT SHIFT
    byte z = (byte) 3;
    byte leftShiftedY = (byte)(z << 2);
    System.out.println(leftShiftedY);
  }

  private static void resolve(byte permission) {
    boolean canRead = (permission & READ) == READ;
    boolean canWrite = (permission & WRITE) == WRITE;
    boolean canExecute = (permission & EXECUTE) == EXECUTE;

    System.out.println("canRead=" + canRead);
    System.out.println("canWrite=" + canWrite);
    System.out.println("canExecute=" + canExecute);
    System.out.println("================");
  }

}
