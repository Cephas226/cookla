using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace RestaurantApp.Models
{
    public class OrderMaster
    {
        [Key]
        public long OrderMasterId { get; set; }

        [Column(TypeName="nvarchar(75)")]
        public string OrderNumber { get; set; }

        public int CustomerId { get; set; }
        public Customers Customer { get; set; }

        [Column(TypeName = "nvarchar(10)")]
        public string PMethod { get; set; }

        public decimal GTotal { get; set; }

        public List<OrderDetail> OrderDetails { get; set; }
    }
}
