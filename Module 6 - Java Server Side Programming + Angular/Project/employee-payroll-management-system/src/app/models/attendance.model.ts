export interface Attendance {
  id: number;
  employeeId: number;
  date: Date;
  status: string; // e.g., 'Present', 'Absent', 'Leave'
}
