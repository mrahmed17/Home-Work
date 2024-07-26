export interface Leave {
  id: number;
  employeeId: number;
  startDate: Date;
  endDate: Date;
  reason: string;
  status: 'Pending' | 'Approved' | 'Rejected';
}
