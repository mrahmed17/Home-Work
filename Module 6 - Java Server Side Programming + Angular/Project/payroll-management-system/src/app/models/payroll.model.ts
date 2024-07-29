export interface Payroll {
  id: number;
  employeeId: number;
  month: string;
  year: number;
  basicSalary: number;
  bonuses?: number;
  deductions?: number;
  netSalary: number;
  payDate: Date;
}
