export interface Leave {
  id: number;
  employeeId: number;
  startDate: Date;
  endDate: Date;
  reason: string;
  status: string; // e.g., 'Pending', 'Approved', 'Rejected'
}
