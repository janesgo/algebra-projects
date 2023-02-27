using IISApi.Models;
using IISApi.Services;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.IdentityModel.Tokens;
using System.Text;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers().AddXmlDataContractSerializerFormatters();

builder.Services.AddSingleton(new TokenService());
builder.Services.AddSingleton<IUserService>(new UserService());
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme).AddJwtBearer(opt =>
{
    opt.TokenValidationParameters = new()
    {
        ValidIssuer = builder.Configuration["Jwt:Issuer"],
        ValidAudience = builder.Configuration["Jwt:Audience"],
        IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(builder.Configuration["Jwt:Key"]))
    };
});
builder.Services.AddAuthorization(o => o.AddPolicy("AdminsOnly",
    b => b.RequireRole("admin", "true")));

var app = builder.Build();

// Configure the HTTP request pipeline.

app.UseAuthentication();
app.UseAuthorization();

app.MapControllers();

app.MapPost("/login", [AllowAnonymous] async ([FromBodyAttribute] User userCheck, IUserService userService, TokenService tokenService, HttpResponse response) =>
{
    var user = userService.IsLoginValid(userCheck);
    if (user.Item1)
    {
        userCheck.Role = user.Item2 ?? "guest";
        var token = tokenService.GenerateToken(userCheck, builder.Configuration["Jwt:Key"], builder.Configuration["Jwt:Issuer"], builder.Configuration["Jwt:Audience"]);
        await response.WriteAsJsonAsync(new { token = token });
    }

    return;
});

app.Run();
