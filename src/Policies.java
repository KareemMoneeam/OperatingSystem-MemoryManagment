import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Policies {
    LinkedList<String> ProcessName = new LinkedList<>();
    LinkedList<Integer> ProcessSize = new LinkedList<>();
    int numOfProcesses;
    int numOfPartitions;
    String pname;
    int minindex;
    int worindex;
    String partname;
    int extraSize;
    boolean flag=false;
    int count =0 ;
    LinkedList<String> PartitionName = new LinkedList<>();
    LinkedList<Integer> PartitionSize = new LinkedList<>();
    LinkedList<String> UsedPartitionName = new LinkedList<>();
    LinkedList<Integer> UsedPartitionSize = new LinkedList<>();
    LinkedList<String> AllocatedProcesses = new LinkedList<>();
    Process p = null;
    Partition part= null;
    List<Partition> partitions = new ArrayList<>();
    List<Process> processes = new ArrayList<>();

    public void getPartitionInfo(Scanner input)
    {
        System.out.print("Enter the number of Partitions: ");
        numOfPartitions = input.nextInt();
        System.out.println("Enter partitions common name: ");
        partname = (input.next());

        for (int i = 0; i < numOfPartitions; i++)
        {
            PartitionName.add(partname +" "+ i);
            System.out.println ("Enter " + PartitionName.get(i) + " size: ");
            PartitionSize.add(input.nextInt());
            part = new Partition(PartitionName.get(i),PartitionSize.get(i));
            partitions.add(part);
        }
    }

    public void getProcessInfo(Scanner input)
    {
        System.out.print("Enter the number of Processes: ");
        numOfProcesses = input.nextInt();
        System.out.println("Enter processes common name: ");
        pname = (input.next());

        for (int i = 0; i < numOfProcesses; i++)
        {
            ProcessName.add(pname +" "+ (i+1));
            System.out.println ("Enter " + ProcessName.get(i) + " size: ");
            ProcessSize.add(input.nextInt());
            p = new Process(ProcessName.get(i),ProcessSize.get(i));
            processes.add(p);
        }

    }
    public void BestFit()
    {
        for (int i=0;i<numOfProcesses;i++)
        {
            minindex=-1;
            for(int j=0;j<numOfPartitions;j++)
            {
                if(ProcessSize.get(i)<=PartitionSize.get(j))
                {
                    if (minindex==-1)
                    {
                        minindex=j; flag=true;
                    }
                    else if(PartitionSize.get(j)<PartitionSize.get(minindex))
                    {
                        minindex=j;
                        flag=true;
                    }
                }
                if (PartitionSize.size()==1)
                {
                    break;
                }
            }
            if (flag==true){
                extraSize=PartitionSize.get(minindex)-ProcessSize.get(i);
                PartitionSize.set(minindex,0);
                UsedPartitionName.add(PartitionName.get(minindex));
                UsedPartitionSize.add(ProcessSize.get(i));
                AllocatedProcesses.add(ProcessName.get(i));
                ProcessName.remove(i);
                ProcessSize.remove(i);
                if (extraSize!=0)
                {
                    PartitionSize.add(minindex+1,extraSize);
                    PartitionName.add(minindex+1, partname+" "+ (numOfPartitions+count));
                    count++;
                }
                flag=true;
                numOfProcesses--;
                i--;
            }
            if (flag!=true)
            {
                System.out.println(ProcessName.get(i) + " can not be allocated ");
            }
            flag=false;
        }
        for(int x=0;x<UsedPartitionName.size();x++)
        {
            System.out.println(UsedPartitionName.get(x) +" (" + UsedPartitionSize.get(x) + " KB) => " + AllocatedProcesses.get(x));
        }
        for(int x=0;x<PartitionSize.size();x++)
        {
            if (PartitionSize.get(x)!=0)
            {
                System.out.println(PartitionName.get(x) + " (" + PartitionSize.get(x) + " KB) => External fragment ");
            }
        }
    }
    public void FirstFit()
    {
        for (int i=0;i<numOfProcesses;i++)
        {
            for(int j=0;j<numOfPartitions;j++)
            {
                if(ProcessSize.get(i)<=PartitionSize.get(j))
                {
                    extraSize=PartitionSize.get(j)-ProcessSize.get(i);
                    PartitionSize.set(j,0);
                    UsedPartitionName.add(PartitionName.get(j));
                    UsedPartitionSize.add(ProcessSize.get(i));
                    AllocatedProcesses.add(ProcessName.get(i));
                    ProcessName.remove(i);
                    ProcessSize.remove(i);
                    if (extraSize!=0)
                    {
                        PartitionSize.add(j+1,extraSize);
                        PartitionName.add(j+1, partname+" "+ (numOfPartitions+count));
                        count++;
                    }
                    flag=true;
                    numOfProcesses--;
                    i--;
                    break;
                }
            }
            if (flag!=true)
            {
                System.out.println(ProcessName.get(i) + " can not be allocated ");
            }
            flag=false;
        }
        for(int x=0;x<UsedPartitionName.size();x++)
        {
            System.out.println(UsedPartitionName.get(x) +" (" + UsedPartitionSize.get(x) + " KB) => " + AllocatedProcesses.get(x));
        }
        for(int x=0;x<PartitionSize.size();x++)
        {
            if (PartitionSize.get(x)!=0)
            {
                System.out.println(PartitionName.get(x) + " (" + PartitionSize.get(x) + " KB) => External fragment ");
            }
        }
    }


    public void WorstFit() {
        for (int i = 0; i < numOfProcesses; i++)
        {
            worindex = -1;
            for (int j = 0; j < numOfPartitions; j++) {
                if (ProcessSize.get(i) <= PartitionSize.get(j)) {
                    if (worindex == -1) {
                        worindex = j;
                        flag = true;
                    } else if (PartitionSize.get(j) > PartitionSize.get(worindex)) {
                        worindex = j;
                        flag = true;
                    }
                }
                if (PartitionSize.size()==1)
                {
                    break;
                }
            }
            if (flag == true)
            {
                extraSize=PartitionSize.get(worindex)-ProcessSize.get(i);
                PartitionSize.set(worindex,0);
                UsedPartitionName.add(PartitionName.get(worindex));
                UsedPartitionSize.add(ProcessSize.get(i));
                AllocatedProcesses.add(ProcessName.get(i));
                ProcessName.remove(i);
                ProcessSize.remove(i);
                if (extraSize != 0)
                {
                    PartitionSize.add(worindex + 1, extraSize);
                    PartitionName.add(worindex + 1, partname + " " + (numOfPartitions + count));
                    count++;
                }
                flag=true;
                numOfProcesses--;
                i--;
            }

            if (flag!=true)
            {
                System.out.println(ProcessName.get(i) + " can not be allocated ");
            }
            flag=false;
        }
        for(int x=0;x<UsedPartitionName.size();x++)
        {
            System.out.println(UsedPartitionName.get(x) +" (" + UsedPartitionSize.get(x) + " KB) => " + AllocatedProcesses.get(x));
        }
        for(int x=0;x<PartitionSize.size();x++)
        {
            if (PartitionSize.get(x)!=0)
            {
                System.out.println(PartitionName.get(x) + " (" + PartitionSize.get(x) + " KB) => External fragment ");
            }
        }

    }
    public void compact()
    {
        int sum=0;
        int flag=0;
        int Size=PartitionSize.size();
        for(int x=0;x<Size;x++)
        {
            if (PartitionSize.get(x)>0)
            {
                flag=1;
                sum+=PartitionSize.get(x);
                PartitionSize.set(x,-1);
            }
        }
        if(flag==1)
        {
            PartitionSize.addLast(sum);
            PartitionName.addLast(partname+" "+ (numOfPartitions+count));
        }
        for(int x=0;x<PartitionSize.size();x++)
        {
            if(PartitionSize.get(x)==-1 ||PartitionSize.get(x)==0)
            {
                PartitionSize.remove(x);
                PartitionName.remove(x);
                x--;
            }
        }
    }
}