# OperatingSystem-MemoryManagment
## A memory allocation simulator to allocate variable-sized partitions of the memory to a given sequence of processes requests.

Memory is divided into partitions; these partitions can either be fixedsized or variable-sized. In case of fixed-sized partitions, each partition 
may contain exactly one process. Thus, the degree of multiprogramming is bound by the number of partitions.
In case of variable-sized partitions, the operating systems keeps a table indicating which parts of memory are available (called holes)
and which are occupied along with their sizes. It then allocates space and loads a process in memory according one of the following
### Policies:

- First fit: in which the first hole that is large enough to fit the process is allocated (fastest method).
- Best fit: in which the smallest hole that is large enough to fit the process is allocated (has the best memory-space utilization).
- Worst fit: in which the largest hole is allocated (has the lowest memory-space utilization).

It is rare to have a perfect fit for a process, so memory allocation usually causes fragmentation. Fragmentation is either external or internal. Internal fragmentation occurs when the memory allocated to
a process is slightly larger than the requested memory while external fragmentation occurs when there is enough total memory space to satisfy a process request but the available spaces are not contiguous.

#### Solutions to external fragmentation:
1. Shuffle the memory contents so as to place all free memory together in one large block. This is called compaction. (used method)
2. Allow the logical address space of the processes to be noncontiguous, thus allowing a process to be allocated physical 
memory wherever such memory is available. This solution can be achieved through segmentation or paging.
