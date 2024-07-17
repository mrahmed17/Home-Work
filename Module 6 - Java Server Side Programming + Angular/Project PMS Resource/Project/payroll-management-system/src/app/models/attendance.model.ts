export interface Attendance {
  id: number;
  employeeId: number;
  date: Date;
  status: 'Present' | 'Absent' | 'Leave' | 'Half-day';
  checkInTime?: Date;
  checkOutTime?: Date;
}
